package ilia.nemankov.utils;

import ilia.nemankov.command.InvalidCommandArgumentsException;

public class Utils {

    public static int parseYear(String line) throws InvalidCommandArgumentsException {
        int year;

        try {
            year = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid year format");
        }

        if (year < 0) {
            throw new InvalidCommandArgumentsException("Year can't be negative");
        }

        return year;
    }

    public static int parseMonth(String line) throws InvalidCommandArgumentsException {
        int month;

        try {
            month = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid month format");
        }

        if (month < 0) {
            throw new InvalidCommandArgumentsException("Month can't be negative");
        }

        if (month > 12) {
            throw new InvalidCommandArgumentsException("Month must be less or equal to 12");
        }

        return month;
    }

    public static int parseDay(String line) throws InvalidCommandArgumentsException {
        int day;

        try {
            day = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new InvalidCommandArgumentsException("Invalid day format");
        }

        if (day < 0) {
            throw new InvalidCommandArgumentsException("Day can't be negative");
        }

        return day;
    }

}
