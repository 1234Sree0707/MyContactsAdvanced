package com.seveneleven.factory;


import com.seveneleven.model.User;
import com.seveneleven.model.FreeUser;
import com.seveneleven.model.PremiumUser;

public class UserFactory {

    public static User createUser(String type, String email, String password, String name) {

        if (type.equalsIgnoreCase("FREE")) {
            return new FreeUser(email, password, name);
        }

        if (type.equalsIgnoreCase("PREMIUM")) {
            return new PremiumUser(email, password, name);
        }

        throw new IllegalArgumentException("Invalid user type");
    }
}