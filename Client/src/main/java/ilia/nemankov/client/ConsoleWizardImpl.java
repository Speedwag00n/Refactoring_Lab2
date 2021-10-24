package ilia.nemankov.client;

import ilia.nemankov.command.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleWizardImpl implements ConsoleWizard {

    private static final String QUIT_COMMAND = "quit";
    private boolean isOn = true;

    @Override
    public void serve() {
        Scanner scanner = new Scanner(System.in);

        while (isOn) {
            System.out.println("Input the command:");

            try {
                String line = scanner.nextLine();
                if (line.trim().equals(QUIT_COMMAND)) {
                    isOn = false;
                    System.out.println("Exiting...");
                } else if (!line.isEmpty()) {
                    CommandFactory commandFactory = new CommandFactoryImpl(scanner);

                    try {
                        Command command = commandFactory.build(line);

                        if (command != null) {
                            CommandResult result = command.execute();
                            if (result.isSuccessful()) {
                                System.out.println(result.getAnswer());
                            } else {
                                System.out.println("Command execution failed. The reason: " + result.getAnswer());
                            }
                        }
                    } catch (InvalidCommandException invalidCommand) {
                        System.out.println("Unknown command. Use \"help\" command for more information");
                    }
                }

                System.out.println("");
            } catch(NoSuchElementException e) {
                scanner.close();
                scanner = new Scanner(System.in);
            }
        }

        scanner.close();
    }

}
