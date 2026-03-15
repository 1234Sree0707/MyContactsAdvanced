package com.seveneleven.service;



import com.seveneleven.builder.UserBuilder;
import com.seveneleven.model.User;
import com.seveneleven.util.PasswordUtil;
import com.seveneleven.util.ValidationUtil;
import com.seveneleven.exception.InvalidInputException;
import com.seveneleven.repository.UserRepository;

public class UserService {

    private UserRepository repo = new UserRepository();

    public User registerUser(String email, String password, String name, String type)
            throws InvalidInputException {

        ValidationUtil.validateEmail(email);
        ValidationUtil.validatePassword(password);

        String hashedPassword = PasswordUtil.hashPassword(password);

        User user = new UserBuilder()
                .setEmail(email)
                .setPassword(hashedPassword)
                .setName(name)
                .setType(type)
                .build();

        repo.save(user);   // ← Integration point

        return user;
    }
}