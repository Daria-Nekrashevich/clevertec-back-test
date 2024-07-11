package ru.clevertec.check.service;

import ru.clevertec.check.model.Product;

import java.util.Map;

public class CalculatorService {
    public double calculateTotalPriceForOneProduct(Product product, int quantity) {
        return product.getPrice() * quantity;
    }

    public double calculateTotalPrice(Map<Product, Integer> productQuantities) {
        return productQuantities.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();
    }

    public double calculateDiscountForOneProduct(Product product, int quantity, int discountAmount) {
        double discount = 0;
        if (product.isWholeSale() == true) {
            discount = product.getPrice() * quantity * 0.10;
        } else {
            discount = product.getPrice() * quantity * discountAmount / 100;
        }
        return discount;
    }

    public double calculateTotalDiscount(Map<Product, Integer> productQuantities, int discountAmount) {
        double totalDiscount = 0;
        for (Map.Entry<Product, Integer> entry : productQuantities.entrySet()) {
            totalDiscount += calculateDiscountForOneProduct(entry.getKey(), entry.getValue(), discountAmount);
        }
        return totalDiscount;
    }

    public double calculateTotalPriceWithDiscount(Map<Product, Integer> productQuantities, int discountAmount) {
        return calculateTotalPrice(productQuantities) - calculateTotalDiscount(productQuantities, discountAmount);
    }

    public boolean isEnoughMoneyOnBalance(int totalPrice, double balance) {
        boolean isEnoughMoney = false;
        if (totalPrice >= balance) {
            isEnoughMoney = true;
        }
        return isEnoughMoney;
    }
}
