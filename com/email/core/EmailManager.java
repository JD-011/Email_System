package com.email.core;

import com.email.models.Email;

import java.io.IOException;
import java.util.List;

public abstract class EmailManager {

    protected String username;

    public EmailManager(String username) {
        this.username = username;
    }

    public abstract void addEmail(Email email) throws IOException;

    public void displayEmails(List<Email> emails) {
        if (emails.isEmpty()) {
            System.out.println("\nNo emails found.");
        } else {
            for (Email email : emails) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
            }
        }
    }
}
