package ilia.nemankov.command;

import ilia.nemankov.command.impl.DayName;
import ilia.nemankov.command.impl.IntervalLength;
import ilia.nemankov.command.impl.IsLeapYear;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command build(String command, String[] args) throws InvalidCommandException, InvalidCommandArgumentsException {
        Command result;

        switch (command) {
            case "check":
                result = new IsLeapYear(args);
                break;
            case "calc":
                result = new IntervalLength(args);
                break;
            case "day":
                result = new DayName(args);
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + command);
        }

        return result;
    }

}
