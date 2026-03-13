package com.seveneleven.util;

import com.seveneleven.exception.InvalidInputException;

public class ValidationUtil {

    public static void validateEmail(String email) throws InvalidInputException {

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (email == null || !email.matches(regex)) {
            throw new InvalidInputException("Invalid Email Format");
        }
    }

    public static void validatePassword(String password) throws InvalidInputException {

        if (password.length() < 6) {
            throw new InvalidInputException("Password must be at least 6 characters");
        }
    }
}