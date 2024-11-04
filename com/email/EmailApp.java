package com.email;

import com.email.auth.AuthService;
import com.email.core.EmailService;
import com.email.core.Inbox;
import com.email.core.Outbox;
import com.email.models.Email;
import com.email.notifications.Notification;
import com.email.notifications.EmailNotification;
import com.email.notifications.SMSNotification;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class EmailApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("\nWelcome to the Email System!");
        label:
        while (true) {
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    handleRegister();
                    break;
                case "2":
                    handleLogin();
                    break;
                case "3":
                    break label;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void handleRegister() throws IOException {
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();

        System.out.println("Enter password:");
        System.out.println("(Your password must contains at least 6 characters & it should be the combination of upper and lower english alphabets, numbers and special characters.)");
        String password = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        if (AuthService.registerUser(username, password, email)) {
            System.out.println("Registration successful!");
        }
    }

    private static void handleLogin() throws IOException {
        Notification notification;
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (AuthService.loginUser(username, password)) {
            notification = new SMSNotification(); // Or EmailNotification
            notification.sendNotification("Login successful!");
            showUserMenu(username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showUserMenu(String username) throws IOException {
        Inbox inbox = new Inbox(username);
        Outbox outbox = new Outbox(username);
        Notification notification;

        List<Email> emails = inbox.getEmails();
        long unreadCount = emails.stream().filter(email -> !email.isRead()).count();

        if (unreadCount > 0) {
            // Use the Notification interface to send a notification
            notification = new EmailNotification();
            notification.sendNotification("You have " + unreadCount + " unread emails.");
        }

        label:
        while (true) {
            System.out.println();
            System.out.println("1. Compose Email");
            System.out.println("2. View Inbox");
            System.out.println("3. View Outbox");
            System.out.println("4. Search Emails");
            System.out.println("5. Delete Email");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    composeEmail(username);
                    break;
                case "2":
                    inbox.loadAndDisplayInbox();
                    break;
                case "3":
                    outbox.loadAndDisplayOutbox();
                    break;
                case "4":
                    System.out.print("Enter 1 for searching email from inbox OR Enter 2 for outbox: ");
                    int temp = sc.nextInt();
                    sc.nextLine();
                    if(temp == 1) searchEmailsFromInbox(username);
                    else if(temp == 2) searchEmailsFromOutbox(username);
                    else System.out.println("Invalid choice!");
                    break;
                case "5":
                    System.out.print("Enter 1 for deleting email from inbox OR Enter 2 for outbox: ");
                    int t = sc.nextInt();
                    sc.nextLine();
                    if(t == 1) deleteEmailFromInbox(username);
                    else if(t == 2) deleteEmailFromOutbox(username);
                    else System.out.println("Invalid choice!");
                    break;
                case "6":
                    notification = new SMSNotification(); // Or EmailNotification
                    notification.sendNotification("You have successfully logged out.");
                    break label;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void composeEmail(String sender) throws IOException {
        System.out.println();
        System.out.print("Enter recipient: ");
        String recipient = sc.nextLine();

        System.out.print("Enter subject: ");
        String subject = sc.nextLine();

        System.out.println("Enter message:");
        String message = sc.nextLine();

        Email email = new Email(sender, recipient, subject, message);

        Outbox outbox = new Outbox(sender);
        Inbox recipientInbox = new Inbox(recipient);

        outbox.addEmail(email);
        recipientInbox.addEmail(email);

        System.out.println("Email sent successfully!");
    }

    private static void searchEmailsFromInbox(String username) throws IOException {
        System.out.println("Search by:");
        System.out.println("1. Subject");
        System.out.println("2. Sender");
        System.out.println("3. Both Subject and Sender");
        System.out.print("Enter your choice: ");

        String choice = sc.nextLine();
        System.out.println();

        switch (choice) {
            case "1" -> {
                System.out.print("Enter subject: ");
                String subject = sc.nextLine();
                EmailService.searchEmailsFromInbox(username, subject);
            }
            case "2" -> {
                System.out.print("Enter sender: ");
                String sender = sc.nextLine();
                EmailService.searchEmailsBySenderFromInbox(username, sender);
            }
            case "3" -> {
                System.out.print("Enter subject: ");
                String subject = sc.nextLine();
                System.out.print("Enter sender: ");
                String sender = sc.nextLine();
                EmailService.searchEmailsFromInbox(username, subject, sender);
            }
            default ->
                System.out.println("Invalid choice!");
        }
    }

    private static void searchEmailsFromOutbox(String username) throws IOException {
        System.out.println("Search by:");
        System.out.println("1. Subject");
        System.out.println("2. recipient");
        System.out.println("3. Both Subject and recipient");
        System.out.print("Enter your choice: ");

        String choice = sc.nextLine();
        System.out.println();

        switch (choice) {
            case "1" -> {
                System.out.print("Enter subject: ");
                String subject = sc.nextLine();
                EmailService.searchEmailsFromOutbox(username, subject);
            }
            case "2" -> {
                System.out.print("Enter recipient: ");
                String recipient = sc.nextLine();
                EmailService.searchEmailsByRecipientFromOutbox(username, recipient);
            }
            case "3" -> {
                System.out.print("Enter subject: ");
                String subject = sc.nextLine();
                System.out.print("Enter recipient: ");
                String recipient = sc.nextLine();
                EmailService.searchEmailsFromOutbox(username, subject, recipient);
            }
            default ->
                    System.out.println("Invalid choice!");
        }
    }

    private static void deleteEmailFromInbox(String username) throws IOException {
        System.out.print("Enter the sender of the email to delete: ");
        String sender = sc.nextLine();

        System.out.print("Enter the subject of the email to delete: ");
        String subject = sc.nextLine();

        FileHandler.deleteEmailFromInbox(username + "_inbox.txt", sender, subject);
    }

    private static void deleteEmailFromOutbox(String username) throws IOException {
        System.out.print("Enter the recipient of the email to delete: ");
        String recipient = sc.nextLine();

        System.out.print("Enter the subject of the email to delete: ");
        String subject = sc.nextLine();

        FileHandler.deleteEmailFromOutbox(username + "_outbox.txt", recipient, subject);
    }
}
