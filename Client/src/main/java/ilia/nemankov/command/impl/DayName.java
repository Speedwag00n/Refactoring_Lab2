package ilia.nemankov.command.impl;

import ilia.nemankov.command.InvalidCommandArgumentsException;
import ilia.nemankov.command.ServerCommand;
import ilia.nemankov.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DayName extends ServerCommand {

    private int year = -1;
    private int month = -1;
    private int day = -1;
    private Date date;

    @Override
    public void configure(String line) throws InvalidCommandArgumentsException {
        if (year == -1) {
            this.year = Utils.parseYear(line);
        } else if (month == -1) {
            this.month = Utils.parseMonth(line);
        } else if (day == -1) {
            int day = Utils.parseDay(line);

            int lastDate = new GregorianCalendar(this.year, this.month - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
            if (lastDate < day) {
                throw new InvalidCommandArgumentsException("Invalid day number. The last day of " + this.year + " " + this.month + " is " + lastDate);
            }

            this.date = new GregorianCalendar(this.year, this.month - 1, day).getTime();
            this.day = day;
        }
    }

    @Override
    public boolean isConfigured() {
        return (date != null);
    }

    @Override
    public String getHint() {
        if (year == -1) {
            return "Input the year:";
        }

        if (month == -1) {
            return "Input the month:";
        }

        if (day == -1) {
            return "Input the day:";
        }

        return null;
    }

    @Override
    public String buildServerRequest() {
        return "day " + this.date.getTime();
    }
}
