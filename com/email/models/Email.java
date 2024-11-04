package com.email.models;

import java.util.ArrayList;
import java.util.List;

public class Email {
    private final String sender;
    private final String recipient;
    private final String subject;
    private final String message;
    private boolean isRead;

    public Email(String sender, String recipient, String subject, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.isRead = false;
    }

    public Email(String sender, String recipient, String subject, String message, boolean isRead) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.isRead = isRead;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String toString() {
        return sender + " | " + recipient + " | " + subject + " | " + message + " | " + isRead;
    }

    public static List<Email> fromFileData(List<String> emailsData) {
        List<Email> emails = new ArrayList<>();
        for (String data : emailsData) {
            String[] parts = data.split(" \\| ");  // Split by the delimiter " | "

            if (parts.length == 5) {  // Check if the array has all 5 parts
                String sender = parts[0];
                String recipient = parts[1];
                String subject = parts[2];
                String message = parts[3];
                boolean isRead = Boolean.parseBoolean(parts[4]);

                emails.add(new Email(sender, recipient, subject, message, isRead));
            } else {
                System.out.println("Invalid email data: " + data);
            }
        }
        return emails;
    }
}
