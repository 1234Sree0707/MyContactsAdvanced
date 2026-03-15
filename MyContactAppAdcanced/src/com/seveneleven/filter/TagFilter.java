package com.seveneleven.filter;

import com.seveneleven.model.Contact;

public class TagFilter implements ContactFilter {

    private String tag;

    public TagFilter(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean apply(Contact contact) {

        return contact.getTags()
                .stream()
                .anyMatch(t -> t.toLowerCase().equals(tag));
    }
}