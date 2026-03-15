package com.seveneleven.filter;

import java.util.Comparator;
import com.seveneleven.model.Contact;

public interface ContactSortStrategy {

    Comparator<Contact> getComparator();
}