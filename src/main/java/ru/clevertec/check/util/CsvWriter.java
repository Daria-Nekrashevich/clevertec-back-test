package ru.clevertec.check.util;

import ru.clevertec.check.exception.NotEnoughMoneyException;
import ru.clevertec.check.exception.WrongDataException;
import ru.clevertec.check.model.Product;
import ru.clevertec.check.service.CalculatorService;
import ru.clevertec.check.service.DiscountCardService;
import ru.clevertec.check.service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CsvWriter {
    private static final String RESULT_FILE_PATH = "./src/main/result.csv";

    public static void writeToFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    public static void checkWriter(String pathToFile, String pathDiscount, Map<Integer, Integer> productQuantities, int discountNumber, double balanceDebitCard, String saveToFile) {
        try {
            StringBuilder check = new StringBuilder();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now().withNano(0);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            check.append("Date;Time\n")
                    .append(date.toString()).append(';')
                    .append(time.format(timeFormatter)).append("\n\n")
                    .append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");

            ProductService productService = createProductService();
            CalculatorService calculatorService = createCalculatorService();
            DiscountCardService discountCardService = createDiscountCardService();
            int discountAmount = discountCardService.getDiscountAmountByCardNumber(discountNumber, pathDiscount);
            Map<Product, Integer> foundProductQuantities = productService.findProductsByArgs(productQuantities, pathToFile);

            for (Map.Entry<Product, Integer> entry : foundProductQuantities.entrySet()) {
                int quantity = entry.getValue();
                Product product = entry.getKey();
                double discount = calculatorService.calculateDiscountForOneProduct(product, quantity, discountAmount);
                double totalPrice = calculatorService.calculateTotalPriceForOneProduct(product, quantity);

                check.append(quantity).append(';')
                        .append(product.getDescription()).append(';')
                        .append(String.format("%.2f", product.getPrice())).append("$;")
                        .append(String.format("%.2f", discount)).append("$;")
                        .append(String.format("%.2f", totalPrice)).append("$\n");
            }

            check.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n")
                    .append(discountNumber).append(';')
                    .append(discountAmount).append("%\n\n")
                    .append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n")
                    .append(String.format("%.2f", calculatorService.calculateTotalPrice(foundProductQuantities))).append("$;")
                    .append(String.format("%.2f", calculatorService.calculateTotalDiscount(foundProductQuantities, discountAmount))).append("$;")
                    .append(String.format("%.2f", calculatorService.calculateTotalPriceWithDiscount(foundProductQuantities, discountAmount))).append("$\n");

            if (calculatorService.calculateTotalPriceWithDiscount(foundProductQuantities, discountAmount) > balanceDebitCard) {
                throw new NotEnoughMoneyException();
            }

            writeToFile(saveToFile != null ? saveToFile : RESULT_FILE_PATH, check.toString());
            System.out.println(check.toString());

        } catch (NotEnoughMoneyException e) {
            handleException(e, "ERROR\nNOT ENOUGH MONEY", saveToFile != null ? saveToFile : RESULT_FILE_PATH);
        } catch (IOException | WrongDataException e) {
            handleException(e, "ERROR\nBAD REQUEST", saveToFile != null ? saveToFile : RESULT_FILE_PATH);
        }
    }

    private static ProductService createProductService() {
        return new ProductService();
    }

    private static CalculatorService createCalculatorService() {
        return new CalculatorService();
    }

    private static DiscountCardService createDiscountCardService() {
        return new DiscountCardService();
    }

    private static void handleException(Exception e, String message, String filePath) {
        System.err.println(e.getMessage());
        try {
            writeToFile(filePath, message);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
