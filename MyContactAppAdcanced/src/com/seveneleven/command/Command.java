package com.seveneleven.command;

public interface Command {

    void execute();

    void undo();
}