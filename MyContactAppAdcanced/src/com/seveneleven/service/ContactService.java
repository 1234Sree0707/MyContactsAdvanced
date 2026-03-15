package com.seveneleven.service;

import com.seveneleven.model.Contact;
import com.seveneleven.session.SessionManager;

public class ContactService {

    public void addContact(Contact contact) {

        if(SessionManager.getInstance().getLoggedInUser() == null) {
            System.out.println("Please login first");
            return;
        }

        System.out.println("Contact added: " + contact.getName());
    }
}