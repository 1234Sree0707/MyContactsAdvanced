package com.seveneleven.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phones;
    private List<EmailAddress> emails;
    private LocalDateTime createdAt;
    private List<String> tags = new ArrayList<>();
    private int contactCount = 0;   

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

    public void setName(String name) {

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        this.name = name;
    }

    public List<PhoneNumber> getPhones() {
        return phones;
    }

    public List<EmailAddress> getEmails() {
        return emails;
    }

    public abstract String getContactType();
    private boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<String> getTags() {
        return tags;
    }
    

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void incrementContactCount() {
        contactCount++;
    }

    public int getContactCount() {
        return contactCount;
    }
    @Override
    public String toString() {
        return "Contact ID: " + id +
               "\nName: " + name +
               "\nPhones: " + phones +
               "\nEmails: " + emails +
               "\nTags: " + tags +
               "\nCreated At: " + createdAt;
    }
}