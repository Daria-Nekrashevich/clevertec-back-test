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
                    .map(parts -> new Product(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            Double.parseDouble(parts[2].replace(',', '.')),
                            Integer.parseInt(parts[3]),
                            Boolean.parseBoolean(parts[4].replace("+", "true").replace("-", "false"))
                    )).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<DiscountCard> readDiscountCards(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(parts -> new DiscountCard(
                            Integer.parseInt(parts[0]),
                            Integer.parseInt(parts[1]),
                            Integer.parseInt(parts[2])
                    )).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
