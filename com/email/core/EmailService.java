package com.email.core;

import com.email.models.Email;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;

public class EmailService {

    public static void searchEmailsFromInbox(String username, String subject) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_inbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by subject: " + subject);
        for (Email email : emails) {
            if (email.getSubject().equalsIgnoreCase(subject)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }

    public static void searchEmailsBySenderFromInbox(String username, String sender) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_inbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by sender: " + sender);
        for (Email email : emails) {
            if (email.getSender().equalsIgnoreCase(sender)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }

    public static void searchEmailsFromInbox(String username, String subject, String sender) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_inbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by subject: " + subject + " and sender: " + sender);
        for (Email email : emails) {
            if (email.getSubject().equalsIgnoreCase(subject) && email.getSender().equalsIgnoreCase(sender)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }

    public static void searchEmailsFromOutbox(String username, String subject) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_outbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by subject: " + subject);
        for (Email email : emails) {
            if (email.getSubject().equalsIgnoreCase(subject)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }

    public static void searchEmailsByRecipientFromOutbox(String username, String recipient) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_outbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by recipient: " + recipient);
        for (Email email : emails) {
            if (email.getRecipient().equalsIgnoreCase(recipient)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }

    public static void searchEmailsFromOutbox(String username, String subject, String recipient) throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_outbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        boolean flag = true;

        System.out.println("\nSearching emails by subject: " + subject + " and recipient: " + recipient);
        for (Email email : emails) {
            if (email.getSubject().equalsIgnoreCase(subject) && email.getRecipient().equalsIgnoreCase(recipient)) {
                System.out.println("\nFrom: " + email.getSender() + "\nTo: " + email.getRecipient() + "\nSubject: " + email.getSubject() + "\nMessage:\n" + email.getMessage());
                flag = false;
            }
        }
        if(flag) System.out.println("\nNo emails found.");
    }
}
