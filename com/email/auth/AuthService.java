package com.email.auth;

import com.email.models.User;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;

public class AuthService {

    private static final String USERS_FILE = "users.txt";

    public static boolean registerUser(String username, String password, String email) throws IOException {
        if(!User.validateEmail(email)){
            System.out.println("Registration failed! Invalid email.");
            return false;
        }
        if(!User.validatePassword(password)){
            System.out.println("Registration failed! Invalid password.");
            return false;
        }

        List<String> users = FileHandler.readFile(USERS_FILE);
        for (String user : users) {
            String[] userDetails = user.split(",");
            if (userDetails[0].equals(username)) {
                System.out.println("Registration failed! username already exists.");
                return false;
            }
        }

        FileHandler.writeFile(USERS_FILE, username + "," + password + "," + email);
        FileHandler.createUserFiles(username);
        return true;
    }

    public static boolean loginUser(String username, String password) throws IOException {
        List<String> users = FileHandler.readFile(USERS_FILE);
        for (String user : users) {
            String[] userDetails = user.split(",");
            if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
