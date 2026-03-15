package com.seveneleven.command;

import com.seveneleven.model.User;
import com.seveneleven.util.PasswordUtil;

public class ChangePasswordCommand implements ProfileCommand {

    private User user;
    private String newPassword;

    public ChangePasswordCommand(User user, String newPassword) {
        this.user = user;
        this.newPassword = newPassword;
    }

    @Override
    public void execute() {

        String hashed = PasswordUtil.hashPassword(newPassword);
        user.setPassword(hashed);
    }
}