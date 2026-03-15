package com.seveneleven.command;

import com.seveneleven.model.User;

public class UpdateNameCommand implements ProfileCommand {

    private User user;
    private String newName;
    private String oldName;

    public UpdateNameCommand(User user, String newName) {
        this.user = user;
        this.newName = newName;
    }

    @Override
    public void execute() {

        // Save previous state
        oldName = user.getName();

        // Update name
        user.setName(newName);
    }

    @Override
    public void undo() {

        // Restore previous name
        user.setName(oldName);
    }
}