package com.seveneleven.repository;

import java.util.*;

import com.seveneleven.model.Contact;

public class ContactRepository {

    private List<Contact> contacts = new ArrayList<>();

    public void add(Contact contact) {
        contacts.add(contact);
    }

    public Optional<Contact> findById(UUID id) {

        return contacts.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    // NEW METHOD
    public Contact findByName(String name) {

        return contacts.stream()
                .filter(c -> c.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void remove(Contact contact) {
        contacts.remove(contact);
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }
}