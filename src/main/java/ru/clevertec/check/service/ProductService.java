package ru.clevertec.check.service;

import ru.clevertec.check.model.Product;
import ru.clevertec.check.util.CsvReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    public List<Product> getProducts(String path) throws IOException {
        return CsvReader.readProducts(path);
    }

    public Map<Product, Integer> findProductsByArgs(Map<Integer, Integer> productQuantities, String path) throws IOException {
        List<Product> products = getProducts(path);
        Map<Product, Integer> foundProductQuantities = new HashMap<>();
        for (Product product : products) {
            if (productQuantities.containsKey(product.getId())) {
                foundProductQuantities.put(product, productQuantities.get(product.getId()));
            }
        }
        return foundProductQuantities;
    }
}
