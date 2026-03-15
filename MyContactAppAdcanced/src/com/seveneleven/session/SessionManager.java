package com.seveneleven.session;

import com.seveneleven.model.User;

public class SessionManager {

    private static SessionManager instance;   // single object
    private User loggedInUser;

    private SessionManager() {
    }

    public static SessionManager getInstance() {   // <-- THIS METHOD
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void login(User user) {
        this.loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}