package com.seveneleven.model;
import java.util.*;
import java.util.Objects;

public class Tag {

    private String name;
    private Set<Contact> contacts = new HashSet<>();

    public Tag(String name) {

        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Tag cannot be empty");
        }

        this.name = name.toLowerCase();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag = (Tag) o;

        return name.equals(tag.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public Set<Contact> getContacts() {
        return contacts;
    }
}
