package com.seveneleven.search;

import com.seveneleven.model.Contact;

public class NameCriteria implements SearchCriteria {

    private String name;

    public NameCriteria(String name) {
        this.name = name.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getName().toLowerCase().contains(name);
    }
}