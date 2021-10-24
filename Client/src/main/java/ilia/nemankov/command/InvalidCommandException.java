package ilia.nemankov.command;

public class InvalidCommandException extends Exception {

    public InvalidCommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandException(String message) {
        super(message);
    }

}
