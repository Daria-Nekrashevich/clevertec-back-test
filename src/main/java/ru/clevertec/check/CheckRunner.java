package ru.clevertec.check;

import ru.clevertec.check.util.CsvWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckRunner {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> productQuantities = parseProductQuantities(args);
        int discountCardNumber = parseDiscountCardNumber(args);
        double balanceDebitCard = parseBalanceDebitCard(args);

        String pathProducts = "./src/main/resources/products.csv";
        String pathDiscountCards = "./src/main/resources/discountCards.csv";

        CsvWriter.checkWriter(pathProducts, pathDiscountCards, productQuantities, discountCardNumber, balanceDebitCard);
    }

    private static Map<Integer, Integer> parseProductQuantities(String[] args) {
        Map<Integer, Integer> productQuantities = new HashMap<>();
        for (String arg : args) {
            if (arg.matches("\\d+-\\d+")) {
                String[] parts = arg.split("-");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                productQuantities.put(productId, productQuantities.getOrDefault(productId, 0) + quantity);
            }
        }
        return productQuantities;
    }

    private static int parseDiscountCardNumber(String[] args) {
        int discountCardNumber = 0;
        for (String arg : args) {
            if (arg.startsWith("discountCard=")) {
                discountCardNumber = Integer.parseInt(arg.split("=")[1]);
                break;
            }
        }
        return discountCardNumber;
    }

    private static double parseBalanceDebitCard(String[] args) {
        double balanceDebitCard = 0;
        for (String arg : args) {
            if (arg.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Double.parseDouble(arg.split("=")[1]);
                break;
            }
        }
        return balanceDebitCard;
    }
}
