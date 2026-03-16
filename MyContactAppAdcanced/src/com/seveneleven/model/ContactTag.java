package com.seveneleven.model;

import java.time.LocalDateTime;

public class ContactTag {

    private Contact contact;
    private Tag tag;
    private LocalDateTime assignedAt;

    public ContactTag(Contact contact, Tag tag) {
        this.contact = contact;
        this.tag = tag;
        this.assignedAt = LocalDateTime.now();
    }

    public Contact getContact() {
        return contact;
    }

    public Tag getTag() {
        return tag;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
}