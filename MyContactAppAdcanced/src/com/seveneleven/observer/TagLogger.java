package com.seveneleven.observer;

import com.seveneleven.model.Contact;
import com.seveneleven.model.Tag;

public class TagLogger implements TagObserver {

    @Override
    public void onTagAdded(Contact contact, Tag tag) {

        System.out.println("Tag '" + tag.getName() +
                "' applied to contact " + contact.getName());
    }
}