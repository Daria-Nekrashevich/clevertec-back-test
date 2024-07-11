package ru.clevertec.check.exception;

public class InternalServerException extends Exception {
    public InternalServerException() {
        super("ERROR\nINTERNAL SERVER EXCEPTION");
    }

    public InternalServerException(String message) {
        super(message);
    }
}
