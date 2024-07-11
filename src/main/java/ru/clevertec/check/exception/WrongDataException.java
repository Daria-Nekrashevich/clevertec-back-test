package ru.clevertec.check.exception;

public class WrongDataException extends IllegalArgumentException {
    public WrongDataException() {
        super("ERROR\nBAD REQUEST");
    }

    public WrongDataException(String message) {
        super(message);
    }
}
