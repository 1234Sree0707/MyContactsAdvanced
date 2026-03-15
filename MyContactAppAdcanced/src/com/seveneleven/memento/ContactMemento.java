package com.seveneleven.memento;

import com.seveneleven.model.Contact;

public class ContactMemento {

    private Contact state;

    public ContactMemento(Contact contact) {
        this.state = contact; // store reference
    }

    public Contact getSavedState() {
        return state;
    }
}