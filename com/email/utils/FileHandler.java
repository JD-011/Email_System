package com.email.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Reading lines from a file (for users, inbox, outbox)
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

    // Writing a single line to a file (for users, inbox, outbox)
    public static void writeFile(String fileName, String data) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true)); // Append mode
        writer.write(data);
        writer.newLine();
        writer.close();
    }

    // Create inbox and outbox for a new user
    public static void createUserFiles(String username) throws IOException {
        new FileWriter(username + "_inbox.txt", true).close();  // Empty file
        new FileWriter(username + "_outbox.txt", true).close(); // Empty file
    }
}
