package com.seveneleven.model;

public class EmailAddress {

    private String email;

    public EmailAddress(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
    @Override
    public String toString() {
        return email;
    }
}