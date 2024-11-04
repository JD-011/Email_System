package com.email.core;

import com.email.models.Email;
import com.email.utils.FileHandler;

import java.io.IOException;
import java.util.List;

public class Inbox extends EmailManager {

    public Inbox(String username) {
        super(username);
    }

    @Override
    public void addEmail(Email email) throws IOException {
        FileHandler.writeFile(username + "_inbox.txt", email.toString());
    }

    public void loadAndDisplayInbox() throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_inbox.txt");
        List<Email> emails = Email.fromFileData(emailsData);
        if (!emails.isEmpty()) {
            for (Email email : emails) {
                if (!email.isRead()) {
                    email.markAsRead();
                }
            }
        }
        saveUpdatedInbox(emails);
        displayEmails(emails);
    }

    private void saveUpdatedInbox(List<Email> emails) throws IOException {
        FileHandler.clearFile(username + "_inbox.txt");
        for (Email email : emails) {
            FileHandler.writeFile(username + "_inbox.txt", email.toString());
        }
    }

    public List<Email> getEmails() throws IOException {
        List<String> emailsData = FileHandler.readFile(username + "_inbox.txt");
        return Email.fromFileData(emailsData);
    }
}
