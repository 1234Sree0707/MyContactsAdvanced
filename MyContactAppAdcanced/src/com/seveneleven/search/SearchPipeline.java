package com.seveneleven.search;

import java.util.List;
import java.util.stream.Collectors;
import com.seveneleven.model.Contact;

public class SearchPipeline {

    private SearchCriteria criteria;

    public SearchPipeline(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public List<Contact> filter(List<Contact> contacts) {

        return contacts.stream()
                .filter(criteria::matches)
                .collect(Collectors.toList());
    }
}