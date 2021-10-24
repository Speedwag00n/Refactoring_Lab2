package ilia.nemankov.command.impl;

import ilia.nemankov.command.Command;
import ilia.nemankov.command.CommandResult;
import ilia.nemankov.command.InvalidCommandArgumentsException;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class IntervalLength extends Command {

    private Date firstDate;
    private Date secondDate;

    public IntervalLength(String[] args) throws InvalidCommandArgumentsException {
        super(args);
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult();

        long difference = Math.abs(firstDate.getTime() - secondDate.getTime());
        result.setAnswer("Days between these dates: " + TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS));

        return result;
    }

    @Override
    public void parse() throws InvalidCommandArgumentsException {
        if (getArgs().length != 2) {
            throw new InvalidCommandArgumentsException("Two dates in milliseconds must be specified");
        }

        try {
            this.firstDate = new Date(Long.parseLong(getArgs()[0]));
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid format of the first date in milliseconds");
        }

        try {
            this.secondDate = new Date(Long.parseLong(getArgs()[1]));
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid format of the second date in milliseconds");
        }
    }

}
