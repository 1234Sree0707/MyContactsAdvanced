package com.seveneleven.builder;


import com.seveneleven.model.User;
import com.seveneleven.factory.UserFactory;

public class UserBuilder {

    private String email;
    private String password;
    private String name;
    private String type;

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public User build() {
        return UserFactory.createUser(type, email, password, name);
    }
}