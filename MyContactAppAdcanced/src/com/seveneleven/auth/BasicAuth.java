package com.seveneleven.auth;


import java.util.Optional;
import com.seveneleven.model.User;
import com.seveneleven.repository.UserRepository;
import com.seveneleven.util.PasswordUtil;

public class BasicAuth implements AuthenticationStrategy {

    private UserRepository repo = new UserRepository();

    @Override
    public Optional<User> authenticate(String email, String password) {

        Optional<User> user = repo.findByEmail(email);

        if(user.isPresent()) {

            String hashed = PasswordUtil.hashPassword(password);

            if(user.get().getPassword().equals(hashed)) {
                return user;
            }
        }

        return Optional.empty();
    }
}