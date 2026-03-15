package com.seveneleven.filter;

import com.seveneleven.model.Contact;

public interface ContactFilter {

    boolean apply(Contact contact);
}