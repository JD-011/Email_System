package com.email.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public static List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

    public static void writeFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)); // Append mode
        writer.write(data);
        writer.newLine();
        writer.close();
    }

    public static void createUserFiles(String username) throws IOException {
        new FileWriter(username + "_inbox.txt", true).close();
        new FileWriter(username + "_outbox.txt", true).close();
    }

    public static void deleteEmailFromInbox(String fileName, String sender, String subject) throws IOException {
        File inputFile = new File(fileName);
        File tempFile = new File("tempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        boolean emailDeleted = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] emailParts = currentLine.split(" \\| ");

            if (emailParts.length == 5) {
                String currentSender = emailParts[0];
                String currentSubject = emailParts[2];

                if (currentSender.equalsIgnoreCase(sender) && currentSubject.equalsIgnoreCase(subject)) {
                    emailDeleted = true;
                    continue;
                }
            }
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        }

        if (emailDeleted) {
            System.out.println("Email deleted successfully.");
        } else {
            System.out.println("No email found with the given sender and subject.");
        }
    }

    public static void deleteEmailFromOutbox(String fileName, String recipient, String subject) throws IOException {
        File inputFile = new File(fileName);
        File tempFile = new File("tempFile.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String currentLine;
        boolean emailDeleted = false;

        while ((currentLine = reader.readLine()) != null) {
            String[] emailParts = currentLine.split(" \\| ");

            if (emailParts.length == 5) {
                String currentrecipient = emailParts[1];
                String currentSubject = emailParts[2];

                if (currentrecipient.equalsIgnoreCase(recipient) && currentSubject.equalsIgnoreCase(subject)) {
                    emailDeleted = true;
                    continue;
                }
            }
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        if (!inputFile.delete()) {
            System.out.println("Could not delete original file.");
            return;
        }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Could not rename temp file.");
        }

        if (emailDeleted) {
            System.out.println("Email deleted successfully.");
        } else {
            System.out.println("No email found with the given recipient and subject.");
        }
    }

    public static void clearFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));
        writer.close();
    }
}
