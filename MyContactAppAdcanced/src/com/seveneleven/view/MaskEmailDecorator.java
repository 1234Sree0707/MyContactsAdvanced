package com.seveneleven.view;

public class MaskEmailDecorator extends ContactDecorator {

    public MaskEmailDecorator(ContactView contactView) {
        super(contactView);
    }

    @Override
    public String display() {

        String result = contactView.display();

        return result.replaceAll("([a-zA-Z0-9._%+-])[a-zA-Z0-9._%+-]*@", "$1***@");
    }
}