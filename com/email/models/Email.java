package com.email.models;

public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String message;
    private boolean isRead;

    public Email(String sender, String recipient, String subject, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.isRead = false;
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
        return "From: " + sender + "\nSubject: " + subject + "\nMessage: " + message + "\n";
    }
}
