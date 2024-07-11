package ru.clevertec.check.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("ERROR\nNOT ENOUGH MONEY");
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
