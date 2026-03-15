package com.seveneleven.repository;

import java.util.*;

import com.seveneleven.model.User;

public class UserRepository {

    private static Map<String, User> users = new HashMap<>();

    public void save(User user) {
        users.put(user.getEmail(), user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(users.get(email));
    }
}