package com.seveneleven.search;

import com.seveneleven.model.Contact;
import java.util.*;

public class TagCriteria implements SearchCriteria {

    private String tag;

    public TagCriteria(String tag) {
        this.tag = tag.toLowerCase();
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getTags()
                .stream()
                .anyMatch(t -> t.getName().toLowerCase().equals(tag));
    }
}