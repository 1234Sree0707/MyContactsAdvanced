package com.seveneleven.search;

import com.seveneleven.model.Contact;

public class PhoneCriteria implements SearchCriteria {

    private String phone;

    public PhoneCriteria(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getPhones()
                .stream()
                .anyMatch(p -> p.getNumber().contains(phone));
    }
}