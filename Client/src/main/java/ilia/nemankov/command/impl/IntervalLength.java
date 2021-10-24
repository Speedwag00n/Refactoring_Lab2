package ilia.nemankov.command.impl;

import ilia.nemankov.command.InvalidCommandArgumentsException;
import ilia.nemankov.command.ServerCommand;
import ilia.nemankov.utils.Utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class IntervalLength extends ServerCommand {

    private int firstYear = -1;
    private int firstMonth = -1;
    private int firstDay = -1;
    private Date firstDate;

    private int secondYear = -1;
    private int secondMonth = -1;
    private int secondDay = -1;
    private Date secondDate;

    @Override
    public void configure(String line) throws InvalidCommandArgumentsException {
        if (firstYear == -1) {
            this.firstYear = Utils.parseYear(line);
        } else if (firstMonth == -1) {
            this.firstMonth = Utils.parseMonth(line);
        } else if (firstDay == -1) {
            int day = Utils.parseDay(line);

            int lastDate = new GregorianCalendar(this.firstYear, this.firstMonth - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
            if (lastDate < day) {
                throw new InvalidCommandArgumentsException("Invalid day number. The last day of " + this.firstYear + " " + this.firstMonth + " is " + lastDate);
            }

            this.firstDate = new GregorianCalendar(this.firstYear, this.firstMonth - 1, day).getTime();
            this.firstDay = day;
        } else if (secondYear == -1) {
            this.secondYear = Utils.parseYear(line);
        } else if (secondMonth == -1) {
            this.secondMonth = Utils.parseMonth(line);
        } else if (secondDay == -1) {
            int day = Utils.parseDay(line);

            int lastDate = new GregorianCalendar(this.secondYear, this.secondMonth - 1, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
            if (lastDate < day) {
                throw new InvalidCommandArgumentsException("Invalid day number. The last day of " + this.secondYear + " " + this.secondMonth + " is " + lastDate);
            }

            this.secondDate = new GregorianCalendar(this.secondYear, this.secondMonth - 1, day).getTime();
            this.secondDay = day;
        }
    }

    @Override
    public boolean isConfigured() {
        return (firstDate != null && secondDate != null);
    }

    @Override
    public String getHint() {
        if (firstYear == -1) {
            return "Input the year (first date):";
        }

        if (firstMonth == -1) {
            return "Input the month (first date):";
        }

        if (firstDay == -1) {
            return "Input the day (first date):";
        }

        if (secondYear == -1) {
            return "Input the year (second date):";
        }

        if (secondMonth == -1) {
            return "Input the month (second date):";
        }

        if (secondDay == -1) {
            return "Input the day (second date):";
        }

        return null;
    }

    @Override
    public String buildServerRequest() {
        return "calc " + this.firstDate.getTime() + " " + this.secondDate.getTime();
    }
}
