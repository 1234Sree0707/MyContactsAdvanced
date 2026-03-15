package com.seveneleven.view;

public abstract class ContactDecorator implements ContactView {

    protected ContactView contactView;

    public ContactDecorator(ContactView contactView) {
        this.contactView = contactView;
    }

    @Override
    public String display() {
        return contactView.display();
    }
}