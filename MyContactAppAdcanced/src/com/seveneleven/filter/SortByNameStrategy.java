package com.seveneleven.filter;

import java.util.Comparator;
import com.seveneleven.model.Contact;

public class SortByNameStrategy implements ContactSortStrategy {

    @Override
    public Comparator<Contact> getComparator() {

        return Comparator.comparing(Contact::getName);
    }
}