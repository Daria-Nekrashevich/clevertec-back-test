package ru.clevertec.check.service;

import ru.clevertec.check.model.DiscountCard;
import ru.clevertec.check.util.CsvReader;

import java.io.IOException;
import java.util.List;

public class DiscountCardService {
    public List<DiscountCard> getDiscountCards(String path) throws IOException {
        return CsvReader.readDiscountCards(path);
    }

    public int getDiscountAmountByCardNumber(int cardNumber, String path) throws IOException {
        List<DiscountCard> discountCards = getDiscountCards(path);
        int discountAmount = 0;
        for (DiscountCard discountCard : discountCards) {
            if (discountCard.getNumber() == cardNumber) {
                discountAmount = discountCard.getDiscount();
            }
        }
        return discountAmount;
    }
}
