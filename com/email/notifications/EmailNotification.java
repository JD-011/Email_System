package com.email.notifications;

public class EmailNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("\nEmail Notification: " + message);
    }
}
