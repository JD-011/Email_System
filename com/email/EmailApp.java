package com.email;

import com.email.auth.AuthService;
import com.email.core.EmailService;
import java.io.IOException;
import java.util.Scanner;

public class EmailApp {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Email System!");
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
        System.out.print("\nEnter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
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
            System.out.println();
            System.out.println("1. Compose Email");
            System.out.println("2. View Inbox");
            System.out.println("3. View Outbox");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

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
        System.out.print("Enter recipient: ");
        String recipient = sc.nextLine();

        System.out.print("Enter subject: ");
        String subject = sc.nextLine();

        System.out.println("Enter message:");
        String message = sc.nextLine();

        EmailService.sendEmail(sender, recipient, subject, message);
        System.out.println("Email sent successfully!");
    }
}
