package com.seveneleven.service;

import java.util.*;
import com.seveneleven.session.SessionManager;
import com.seveneleven.model.Contact;
import com.seveneleven.repository.ContactRepository;
import com.seveneleven.observer.ContactObserver;
import com.seveneleven.search.*;

public class ContactService {

    private ContactRepository repo;
    private List<ContactObserver> observers = new ArrayList<>();

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    public void addContact(Contact contact) {

        if(SessionManager.getInstance().getLoggedInUser() == null) {
            System.out.println("Please login first");
            return;
        }

        repo.add(contact);   // store contact

        System.out.println("Contact added: " + contact.getName());
    }

    public void softDelete(Contact contact) {
        contact.setDeleted(true);
        notifyObservers(contact);
    }

    public void hardDelete(Contact contact) {
        repo.remove(contact);
        notifyObservers(contact);
    }

    public void registerObserver(ContactObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Contact contact) {
        for(ContactObserver obs : observers) {
            obs.onContactDeleted(contact);
        }
    }
    public void tagContacts(List<Contact> contacts, String tag) {

        for (Contact c : contacts) {
            c.addTag(tag);
        }

        System.out.println("Tag '" + tag + "' added to " + contacts.size() + " contacts.");
    }
    public List<Contact> searchContacts(SearchCriteria criteria) {

        return repo.getAllContacts()
                .stream()
                .filter(criteria::matches)
                .toList();
    }
}