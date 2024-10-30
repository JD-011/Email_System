package com.email.core;

import com.email.models.Email;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;

public class EmailService {

    // Send an email
    public static void sendEmail(String sender, String recipient, String subject, String message) throws IOException {
        Email email = new Email(sender, recipient, subject, message);

        // Add email to recipient's inbox
        FileHandler.writeFile(recipient + "_inbox.txt", email.toString());

        // Add email to sender's outbox
        FileHandler.writeFile(sender + "_outbox.txt", email.toString());
    }

    // Read inbox or outbox emails
    public static void displayEmails(String username, String folder) throws IOException {
        List<String> emails = FileHandler.readFile(username + "_" + folder + ".txt");
        if (emails.isEmpty()) {
            System.out.println("No emails found.");
        } else {
            for (String email : emails) {
                System.out.println(email);
            }
        }
    }
}
