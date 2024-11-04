package com.email.core;

import com.email.models.Email;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;

public class Outbox extends EmailManager {

    public Outbox(String username) {
        super(username);
    }

    @Override
    public void addEmail(Email email) throws IOException {
        FileHandler.writeFile(username + "_outbox.txt", email.toString());
    }

    public void loadAndDisplayOutbox() throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_outbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        displayEmails(emails);
    }
}
