package com.seveneleven.factory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.seveneleven.model.*;

public class ContactFactory {

    public static Contact createContact(String type, UUID id, String name,
                                        List<PhoneNumber> phones,
                                        List<EmailAddress> emails,
                                        LocalDateTime time) {

        if(type.equalsIgnoreCase("PERSON"))
            return new Person(id, name, phones, emails, time);

        if(type.equalsIgnoreCase("ORG"))
            return new Organization(id, name, phones, emails, time);

        throw new IllegalArgumentException("Invalid contact type");
    }
}