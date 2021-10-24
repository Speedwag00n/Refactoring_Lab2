package ilia.nemankov.command;

public interface CommandFactory {

    Command build(String command, String[] args) throws InvalidCommandException, InvalidCommandArgumentsException;

}
