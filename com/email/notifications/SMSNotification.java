package com.email.notifications;

public class SMSNotification implements Notification {

    @Override
    public void sendNotification(String message) {
        System.out.println("\nSMS Notification: " + message);
    }
}
