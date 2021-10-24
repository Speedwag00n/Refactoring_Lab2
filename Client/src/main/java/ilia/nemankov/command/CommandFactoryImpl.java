package ilia.nemankov.command;

import ilia.nemankov.command.impl.DayName;
import ilia.nemankov.command.impl.IntervalLength;
import ilia.nemankov.command.impl.IsLeapYear;
import lombok.AllArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Scanner;

@AllArgsConstructor
public class CommandFactoryImpl implements CommandFactory {

    private Scanner scanner;

    @Override
    public Command build(String command) throws InvalidCommandException {
        command = command.trim();

        Command result = null;

        switch (command) {
            case "help":
                System.out.println(String.join(
                        "Use one of commands:\n",
                        "\"check\" to check is year leap",
                        "\"calc\" to calc interval length",
                        "\"day\" to get the name of day of week",
                        "\"help\" to show this message",
                        "\"quit\" to exit"
                ));
                break;
            case "check":
                result = new IsLeapYear();
                break;
            case "calc":
                result = new IntervalLength();
                break;
            case "day":
                result = new DayName();
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + command);
        }

        if (result != null) {
            while (!result.isConfigured()) {
                System.out.println(result.getHint());

                try {
                    String line = scanner.nextLine();

                    try {
                        result.configure(line);
                    } catch (InvalidCommandArgumentsException e) {
                        System.out.println(e.getMessage());
                    }
                } catch(NoSuchElementException e) {
                    scanner.close();
                    scanner = new Scanner(System.in);
                }
            }
        }

        return result;
    }

}
