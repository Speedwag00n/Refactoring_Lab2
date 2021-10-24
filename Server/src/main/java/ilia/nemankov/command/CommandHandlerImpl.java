package ilia.nemankov.command;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public class CommandHandlerImpl implements CommandHandler {

    private CommandFactory commandFactory;

    @Override
    public CommandResult execute(String request) throws InvalidCommandArgumentsException, InvalidCommandException {
        String[] parts = request.split("\\s+");

        String commandName = parts[0];

        String[] args = null;

        if (parts.length > 1) {
            args = Arrays.copyOfRange(parts, 1, parts.length);
        }

        Command command = commandFactory.build(commandName, args);

        return command.execute();
    }

}
