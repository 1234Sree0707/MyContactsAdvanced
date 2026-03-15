package com.seveneleven.service;

import java.util.*;

import com.seveneleven.session.SessionManager;
import com.seveneleven.model.Contact;
import com.seveneleven.repository.ContactRepository;
import com.seveneleven.observer.ContactObserver;

public class ContactService {

    private ContactRepository repo = new ContactRepository();

    private List<ContactObserver> observers = new ArrayList<>();

    public ContactService(ContactRepository contactRepository) {
		// TODO Auto-generated constructor stub
	}

	public void addContact(Contact contact) {

        if(SessionManager.getInstance().getLoggedInUser() == null) {
            System.out.println("Please login first");
            return;
        }

        repo.add(contact);   

        System.out.println("Contact added: " + contact.getName());
    }

    public void registerObserver(ContactObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Contact contact) {

        for(ContactObserver obs : observers) {
            obs.onContactDeleted(contact);
        }
    }

    public void softDelete(Contact contact) {

        contact.setDeleted(true);

        notifyObservers(contact);
    }

    public void hardDelete(Contact contact) {

        repo.remove(contact);

        notifyObservers(contact);
    }

    public List<Contact> getAllContacts(){
        return repo.getAllContacts();
    }
}