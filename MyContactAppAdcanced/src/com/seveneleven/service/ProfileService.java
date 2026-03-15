package com.seveneleven.service;

import com.seveneleven.command.ProfileCommand;
import com.seveneleven.model.User;
import com.seveneleven.session.SessionManager;

public class ProfileService {

    public void executeCommand(ProfileCommand command) {

        User user = SessionManager.getInstance().getLoggedInUser();

        if (user == null) {
            System.out.println("User not logged in");
            return;
        }

        command.execute();
    }
}