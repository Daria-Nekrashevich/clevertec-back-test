package ru.clevertec.check;

import ru.clevertec.check.util.CsvWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckRunner {
    private static final String RESULT_FILE_PATH = "./src/main/result.csv";

    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> productQuantities = parseProductQuantities(args);
        int discountCardNumber = parseDiscountCardNumber(args);
        double balanceDebitCard = parseBalanceDebitCard(args);
        String pathToFile = parsePathToFile(args);
        String saveToFile = parseSaveToFile(args);

        String pathDiscountCards = "./src/main/resources/discountCards.csv";

        if (pathToFile == null) {
            CsvWriter.writeToFile(saveToFile != null ? saveToFile : RESULT_FILE_PATH, "ERROR\nBAD REQUEST");
        } else {
            CsvWriter.checkWriter(pathToFile, pathDiscountCards, productQuantities, discountCardNumber, balanceDebitCard, saveToFile);
        }
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

    private static String parsePathToFile(String[] args) {
        String pathToFile = null;
        for (String arg : args) {
            if (arg.startsWith("pathToFile=")) {
                pathToFile = arg.split("=")[1];
                break;
            }
        }
        return pathToFile;
    }

    private static String parseSaveToFile(String[] args) {
        String saveToFile = null;
        for (String arg : args) {
            if (arg.startsWith("saveToFile=")) {
                saveToFile = arg.split("=")[1];
                break;
            }
        }
        return saveToFile;
    }
}
