package com.seveneleven.search;

import java.util.regex.Pattern;
import com.seveneleven.model.Contact;

public class EmailCriteria implements SearchCriteria {

    private Pattern pattern;

    public EmailCriteria(String emailPattern) {

        pattern = Pattern.compile(emailPattern, Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean matches(Contact contact) {

        return contact.getEmails()
                .stream()
                .anyMatch(e -> pattern.matcher(e.getEmail()).find());
    }
}