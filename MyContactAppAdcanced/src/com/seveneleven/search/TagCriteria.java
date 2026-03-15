package com.seveneleven.search;

import com.seveneleven.model.Contact;

public class TagCriteria implements SearchCriteria {

    private String tag;

    public TagCriteria(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getTags()
                .stream()
                .anyMatch(t -> t.toLowerCase().equals(tag));
    }
}