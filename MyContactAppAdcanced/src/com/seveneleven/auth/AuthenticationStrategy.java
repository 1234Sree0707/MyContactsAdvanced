package com.seveneleven.auth;


import java.util.Optional;
import com.seveneleven.model.User;

public interface AuthenticationStrategy {
    Optional<User> authenticate(String email, String password);
}
