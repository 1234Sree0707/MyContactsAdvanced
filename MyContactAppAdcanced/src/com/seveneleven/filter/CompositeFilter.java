package com.seveneleven.filter;

import java.util.ArrayList;
import java.util.List;

import com.seveneleven.model.Contact;

public class CompositeFilter implements ContactFilter {

    private List<ContactFilter> filters = new ArrayList<>();

    public void addFilter(ContactFilter filter) {
        filters.add(filter);
    }

    @Override
    public boolean apply(Contact contact) {

        return filters.stream()
                .allMatch(f -> f.apply(contact));
    }
}