package com.example.exercicerecycle;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private boolean isOnline;

    public Contact(String name, boolean isOnline) {
        this.name = name;
        this.isOnline = isOnline;
    }

    public String getName() {
        return name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    private static int lastContactId = 0;
    public static List<Contact> createContactList(int contactNumbers){
        List<Contact> contacts = new ArrayList<>(contactNumbers);

        for (int i = 1; i <= contactNumbers; i++){
            contacts.add(new Contact("Person " + ++lastContactId, i <= contactNumbers/2));
        }

        return contacts;
    }
}
