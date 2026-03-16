package com.seveneleven.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public abstract class Contact {

    private UUID id;
    private String name;
    private List<PhoneNumber> phones;
    private List<EmailAddress> emails;
    private LocalDateTime createdAt;

    // UC-11 change → use Set<Tag>
    private Set<Tag> tags = new HashSet<>();

    private int contactCount = 0;
    private boolean deleted = false;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // =========================
    // UC-11 TAG METHODS
    // =========================

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Set<Tag> getTags() {
        return tags;
    }

    // =========================
    // CONTACT COUNT
    // =========================

    public void incrementContactCount() {
        contactCount++;
    }

    public int getContactCount() {
        return contactCount;
    }

    // =========================
    // DELETE SUPPORT
    // =========================

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    // =========================
    // TYPE
    // =========================

    public abstract String getContactType();

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