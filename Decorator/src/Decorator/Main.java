package Decorator;
// Main.java
public class Main {
    public static void main(String[] args) {
        Notification notification = new BasicNotification();
        notification = new EmailNotification(notification);
        notification = new SMSNotification(notification);
        notification = new PushNotification(notification);

        notification.send("You have a new message!");
    }
}