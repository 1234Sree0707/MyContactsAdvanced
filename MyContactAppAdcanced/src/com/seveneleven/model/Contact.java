package com.seveneleven.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phones;
    private List<EmailAddress> emails;
    private LocalDateTime createdAt;

    public Contact(UUID id, String name, List<PhoneNumber> phones,
                   List<EmailAddress> emails, LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.phones = phones;
        this.emails = emails;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public List<EmailAddress> getEmails() {
        return emails;
    }

    public abstract String getContactType();
}