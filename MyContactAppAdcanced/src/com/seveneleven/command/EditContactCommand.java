package com.seveneleven.command;

import com.seveneleven.model.Contact;
import com.seveneleven.memento.ContactMemento;

public class EditContactCommand implements Command {

    private Contact contact;
    private ContactMemento backup;
    private String newName;

    public EditContactCommand(Contact contact, String newName) {
        this.contact = contact;
        this.newName = newName;
    }

    @Override
    public void execute() {

        backup = new ContactMemento(contact);

        contact.setName(newName);
    }

    @Override
    public void undo() {

        Contact previous = backup.getSavedState();

        contact.setName(previous.getName());
    }
}