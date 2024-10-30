package com.email;

import com.email.auth.AuthService;
import com.email.core.EmailService;
import java.io.IOException;
import java.util.Scanner;

public class EmailApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        label:
        while (true) {
            System.out.println("Welcome to the Email System!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Quit");

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
        System.out.println("Enter username:");
        String username = sc.nextLine();

        System.out.println("Enter password:");
        String password = sc.nextLine();

        System.out.println("Enter email:");
        String email = sc.nextLine();

        if (AuthService.registerUser(username, password, email)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed! Invalid email or password, or username already exists.");
        }
    }

    private static void handleLogin() throws IOException {
        System.out.println("Enter username:");
        String username = sc.nextLine();

        System.out.println("Enter password:");
        String password = sc.nextLine();

        if (AuthService.loginUser(username, password)) {
            System.out.println("Login successful!");
            showUserMenu(username);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void showUserMenu(String username) throws IOException {
        label:
        while (true) {
            System.out.println("1. Compose Email");
            System.out.println("2. View Inbox");
            System.out.println("3. View Outbox");
            System.out.println("4. Logout");

            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    composeEmail(username);
                    break;
                case "2":
                    EmailService.displayEmails(username, "inbox");
                    break;
                case "3":
                    EmailService.displayEmails(username, "outbox");
                    break;
                case "4":
                    break label;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void composeEmail(String sender) throws IOException {
        System.out.println("Enter recipient:");
        String recipient = sc.nextLine();

        System.out.println("Enter subject:");
        String subject = sc.nextLine();

        System.out.println("Enter message:");
        String message = sc.nextLine();

        EmailService.sendEmail(sender, recipient, subject, message);
        System.out.println("Email sent successfully!");
    }
}
