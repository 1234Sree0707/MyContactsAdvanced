package com.seveneleven.observer;

import com.seveneleven.model.Contact;

public interface ContactObserver {

    void onContactDeleted(Contact contact);
}