package com.seveneleven.observer;

import com.seveneleven.model.Contact;
import com.seveneleven.model.Tag;

public interface TagObserver {

    void onTagAdded(Contact contact, Tag tag);
}