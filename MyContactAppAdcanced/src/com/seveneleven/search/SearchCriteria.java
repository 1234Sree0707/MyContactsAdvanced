package com.seveneleven.search;

import com.seveneleven.model.Contact;

public interface SearchCriteria {

    boolean matches(Contact contact);
}