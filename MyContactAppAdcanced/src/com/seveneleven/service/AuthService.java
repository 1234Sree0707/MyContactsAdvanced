package com.seveneleven.service;


import java.util.Optional;

import com.seveneleven.auth.AuthenticationStrategy;
import com.seveneleven.auth.BasicAuth;
import com.seveneleven.model.User;
import com.seveneleven.session.SessionManager;


public class AuthService {

    private AuthenticationStrategy authStrategy = new BasicAuth();

    public boolean login(String email, String password) {

        Optional<User> user = authStrategy.authenticate(email, password);

        if(user.isPresent()) {

            SessionManager.getInstance().login(user.get());
            return true;
        }

        return false;
    }
}