package ru.clevertec.check.util;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CsvReader {
    public static List<Product> readProducts(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(parts -> createProduct(parts))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Product createProduct(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        String description = parts[1];
        double price = Double.parseDouble(parts[2].replace(',', '.'));
        int quantity = Integer.parseInt(parts[3]);
        boolean wholeSale = Boolean.parseBoolean(parts[4].replace("+", "true").replace("-", "false"));
        return new Product(id, description, price, quantity, wholeSale);
    }

    public static List<DiscountCard> readDiscountCards(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(parts -> createDiscountCard(parts))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static DiscountCard createDiscountCard(String[] parts) {
        int id = Integer.parseInt(parts[0]);
        int number = Integer.parseInt(parts[1]);
        int discount = Integer.parseInt(parts[2]);
        return new DiscountCard(id, number, discount);
    }
}
