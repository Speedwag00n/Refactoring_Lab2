package ilia.nemankov.command;

public class InvalidCommandArgumentsException extends Exception {

    public InvalidCommandArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCommandArgumentsException(String message) {
        super(message);
    }

}
