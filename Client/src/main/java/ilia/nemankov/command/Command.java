package ilia.nemankov.command;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public abstract class Command {

    public abstract CommandResult execute();

    public abstract void configure(String line) throws InvalidCommandArgumentsException;

    public abstract boolean isConfigured();

    public abstract String getHint();

}
