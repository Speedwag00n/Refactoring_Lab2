package ilia.nemankov.command.impl;

import ilia.nemankov.command.Command;
import ilia.nemankov.command.CommandResult;
import ilia.nemankov.command.InvalidCommandArgumentsException;

public class IsLeapYear extends Command {

    private int year;

    public IsLeapYear(String[] args) throws InvalidCommandArgumentsException {
        super(args);
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult();

        if (year % 4 != 0 || year % 100 == 0) {
            result.setAnswer("Is year " + year + " leap? False");
        } else {
            result.setAnswer("Is year " + year + " leap? True");
        }

        return result;
    }

    @Override
    public void parse() throws InvalidCommandArgumentsException {
        if (getArgs().length != 1) {
            throw new InvalidCommandArgumentsException("Year must be specified");
        }

        try {
            this.year = Integer.parseInt(getArgs()[0]);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid year format");
        }

        if (this.year < 0) {
            throw new InvalidCommandArgumentsException("Year can't be negative");
        }
    }

}
