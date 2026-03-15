package com.seveneleven.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Organization extends Contact {

    public Organization(UUID id, String name,
                        List<PhoneNumber> phones,
                        List<EmailAddress> emails,
                        LocalDateTime createdAt) {

        super(id, name, phones, emails, createdAt);
    }

    @Override
    public String getContactType() {
        return "ORGANIZATION";
    }
}