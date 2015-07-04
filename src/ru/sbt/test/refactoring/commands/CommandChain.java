package ru.sbt.test.refactoring.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by artem on 04.07.15.
 */


public abstract class CommandChain {
    private List<CommandChain> commands = new ArrayList<CommandChain>();
    public void execute() {
        executeSelf();
        for (CommandChain command : commands) {
            command.execute();
        }
    }

    public CommandChain addCommandToQueuee(CommandChain commandChain) {
        commands.add(commandChain);
        return this;
    }

    abstract protected void executeSelf();
}
