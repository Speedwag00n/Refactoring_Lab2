package ilia.nemankov.command;

public interface CommandFactory {

    Command build(String command) throws InvalidCommandException;

}
