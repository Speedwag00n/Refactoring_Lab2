package ilia.nemankov.command.impl;

import ilia.nemankov.command.InvalidCommandArgumentsException;
import ilia.nemankov.command.ServerCommand;
import ilia.nemankov.utils.Utils;

public class IsLeapYear extends ServerCommand {

    private int year = -1;

    @Override
    public void configure(String line) throws InvalidCommandArgumentsException {
        this.year = Utils.parseYear(line);
    }

    @Override
    public boolean isConfigured() {
        return (year != -1);
    }

    @Override
    public String getHint() {
        if (year == -1) {
            return "Input the year:";
        }

        return null;
    }

    @Override
    public String buildServerRequest() {
        return "check " + this.year;
    }
}
