package com.seveneleven.builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.seveneleven.factory.ContactFactory;
import com.seveneleven.model.*;

public class ContactBuilder {

    private String name;
    private List<PhoneNumber> phones = new ArrayList<>();
    private List<EmailAddress> emails = new ArrayList<>();
    private String type;

    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ContactBuilder addPhone(String phone) {
        phones.add(new PhoneNumber(phone));
        return this;
    }

    public ContactBuilder addEmail(String email) {
        emails.add(new EmailAddress(email));
        return this;
    }

    public ContactBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public Contact build() {

        UUID id = UUID.randomUUID();
        LocalDateTime time = LocalDateTime.now();

        return ContactFactory.createContact(type, id, name, phones, emails, time);
    }
}