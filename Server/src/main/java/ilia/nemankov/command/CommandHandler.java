package ilia.nemankov.command;

public interface CommandHandler {

    CommandResult execute(String request) throws InvalidCommandArgumentsException, InvalidCommandException;

}
