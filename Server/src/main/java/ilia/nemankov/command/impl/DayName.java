package ilia.nemankov.command.impl;

import ilia.nemankov.command.Command;
import ilia.nemankov.command.CommandResult;
import ilia.nemankov.command.InvalidCommandArgumentsException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DayName extends Command {

    private Date date;

    public DayName(String[] args) throws InvalidCommandArgumentsException {
        super(args);
    }

    @Override
    public CommandResult execute() {
        CommandResult result = new CommandResult();

        result.setAnswer(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date));

        return result;
    }

    @Override
    public void parse() throws InvalidCommandArgumentsException {
        if (getArgs().length != 1) {
            throw new InvalidCommandArgumentsException("Date in milliseconds must be specified");
        }

        try {
            this.date = new Date(Long.parseLong(getArgs()[0]));
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid format of date in milliseconds");
        }
    }

}
