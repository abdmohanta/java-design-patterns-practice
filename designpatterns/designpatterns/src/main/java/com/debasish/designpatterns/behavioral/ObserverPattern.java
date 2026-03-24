package com.debasish.designpatterns.behavioral;

public class ObserverPattern {

    public static void main(String[] args) {

        // Observer interface - defines update method
        interface Observer {
            void update(String message);
        }

        // Subject interface - defines observer management
        interface Subject {
            void attach(Observer observer);
            void detach(Observer observer);
            void notifyObservers();
        }

        // Concrete Observer 1 - Email Subscriber
        class EmailSubscriber implements Observer {
            private String name;

            public EmailSubscriber(String name) {
                this.name = name;
            }

            @Override
            public void update(String message) {
                System.out.println("Email to " + name + ": " + message);
            }
        }

        // Concrete Observer 2 - SMS Subscriber
        class SMSSubscriber implements Observer {
            private String phoneNumber;

            public SMSSubscriber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            @Override
            public void update(String message) {
                System.out.println("SMS to " + phoneNumber + ": " + message);
            }
        }

        // Concrete Observer 3 - Notification Subscriber
        class NotificationSubscriber implements Observer {
            private String deviceId;

            public NotificationSubscriber(String deviceId) {
                this.deviceId = deviceId;
            }

            @Override
            public void update(String message) {
                System.out.println("Notification to device " + deviceId + ": " + message);
            }
        }

        // Concrete Subject - News Publisher
        class NewsPublisher implements Subject {
            private java.util.List<Observer> observers = new java.util.ArrayList<>();
            private String latestNews;

            @Override
            public void attach(Observer observer) {
                if (!observers.contains(observer)) {
                    observers.add(observer);
                    System.out.println("Observer attached. Total observers: " + observers.size());
                }
            }

            @Override
            public void detach(Observer observer) {
                if (observers.remove(observer)) {
                    System.out.println("Observer detached. Total observers: " + observers.size());
                }
            }

            @Override
            public void notifyObservers() {
                for (Observer observer : observers) {
                    observer.update(latestNews);
                }
            }

            public void publishNews(String news) {
                System.out.println("\n>>> Publishing News: " + news);
                this.latestNews = news;
                notifyObservers();
            }
        }

        // Demonstrate Observer Pattern
        System.out.println("=== Observer Pattern Demo ===\n");

        // Create Subject (Publisher)
        NewsPublisher publisher = new NewsPublisher();

        // Create Observers (Subscribers)
        EmailSubscriber emailSubscriber1 = new EmailSubscriber("john@example.com");
        EmailSubscriber emailSubscriber2 = new EmailSubscriber("jane@example.com");
        SMSSubscriber smsSubscriber = new SMSSubscriber("+1-555-0123");
        NotificationSubscriber notificationSubscriber = new NotificationSubscriber("Device-001");

        // Attach observers to subject
        System.out.println("--- Attaching Observers ---");
        publisher.attach(emailSubscriber1);
        publisher.attach(emailSubscriber2);
        publisher.attach(smsSubscriber);
        publisher.attach(notificationSubscriber);

        // Publish first news - all observers get notified
        publisher.publishNews("Breaking News: New Java version released!");

        // Detach one observer
        System.out.println("\n--- Detaching SMS Subscriber ---");
        publisher.detach(smsSubscriber);

        // Publish second news - SMS subscriber won't be notified
        publisher.publishNews("Update: Java 21 features explained");

        // Attach a new observer
        System.out.println("\n--- Attaching New Email Subscriber ---");
        EmailSubscriber emailSubscriber3 = new EmailSubscriber("mike@example.com");
        publisher.attach(emailSubscriber3);

        // Publish third news - new subscriber gets notified
        publisher.publishNews("Special: Design Patterns tutorial available now");

        // Detach all observers
        System.out.println("\n--- Detaching All Observers ---");
        publisher.detach(emailSubscriber1);
        publisher.detach(emailSubscriber2);
        publisher.detach(notificationSubscriber);
        publisher.detach(emailSubscriber3);

        // Publish news - no observers to notify
        publisher.publishNews("Regular update: No subscribers listening");
    }

}
