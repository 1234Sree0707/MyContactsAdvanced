package com.seveneleven.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Person extends Contact {

    public Person(UUID id, String name, List<PhoneNumber> phones,
                  List<EmailAddress> emails, LocalDateTime createdAt) {

        super(id, name, phones, emails, createdAt);
    }

    @Override
    public String getContactType() {
        return "PERSON";
    }
}