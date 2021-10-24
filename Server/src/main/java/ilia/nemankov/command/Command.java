package ilia.nemankov.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Command {

    private String[] args;

    public Command(String args[]) throws InvalidCommandArgumentsException {
        this.args = args;
        parse();
    }

    public abstract CommandResult execute();

    public abstract void parse() throws InvalidCommandArgumentsException;

}
