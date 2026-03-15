package com.seveneleven.view;

public class UpperCaseNameDecorator extends ContactDecorator {

    public UpperCaseNameDecorator(ContactView contactView) {
        super(contactView);
    }

    @Override
    public String display() {

        String result = contactView.display();

        return result.toUpperCase();
    }
}