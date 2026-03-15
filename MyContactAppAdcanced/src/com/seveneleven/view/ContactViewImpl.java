package com.seveneleven.view;

import com.seveneleven.model.Contact;

public class ContactViewImpl implements ContactView {

    private Contact contact;

    public ContactViewImpl(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String display() {

        return "Contact ID: " + contact.getId() +
               "\nName: " + contact.getName() +
               "\nPhones: " + contact.getPhones() +
               "\nEmails: " + contact.getEmails();
    }
}