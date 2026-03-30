package com.debasish.designpatterns.behavioral;

public class StrategyPattern {

    public static void main(String[] args) {
        // Strategy interface - defines algorithm family
        interface PaymentStrategy {
            void pay(double amount);
        }

        // Concrete Strategy 1 - Credit Card Payment
        class CreditCardPayment implements PaymentStrategy {
            private String cardNumber;
            private String cardHolder;
            private String cvv;
            public CreditCardPayment(String cardNumber, String cardHolder, String cvv) {
                this.cardNumber = cardNumber;
                this.cardHolder = cardHolder;
                this.cvv = cvv;
            }

            @Override
            public void pay(double amount) {
                System.out.println("Processing payment via Credit Card");
                System.out.println("Card Number: " + maskCardNumber(cardNumber));
                System.out.println("Card Holder: " + cardHolder);
                System.out.println("Amount: $" + amount);
                System.out.println("✓ Payment successful via Credit Card\n");
            }
            private String maskCardNumber(String cardNumber) {
                return cardNumber.substring(0, 4) + "-****-****-" + cardNumber.substring(12);
            }
        }
        class PayPalPayment implements PaymentStrategy {
            private String email;
            private String password;
            public PayPalPayment(String email, String password) {
                this.email = email;
                this.password = password;
            }
            @Override
            public void pay(double amount) {
                System.out.println("Processing payment via PayPal");
                System.out.println("Email: " + email);
                System.out.println("Amount: $" + amount);
                System.out.println("✓ Payment successful via PayPal\n");
            }
        }
        class BitcoinPayment implements PaymentStrategy {
            private String walletAddress;
            public BitcoinPayment(String walletAddress) {
                this.walletAddress = walletAddress;
            }
            @Override
            public void pay(double amount) {
                System.out.println("Processing payment via Bitcoin");
                System.out.println("Wallet Address: " + walletAddress);
                System.out.println("Amount: $" + amount);
                System.out.println("BTC Amount: " + (amount / 45000) + " BTC");
                System.out.println("✓ Payment successful via Bitcoin\n");
            }
        }
        // Concrete Strategy 4 - Google Pay
        class GooglePayPayment implements PaymentStrategy {
            private String phoneNumber;
            public GooglePayPayment(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }
            @Override
            public void pay(double amount) {
                System.out.println("Processing payment via Google Pay");
                System.out.println("Phone Number: " + phoneNumber);
                System.out.println("Amount: $" + amount);
                System.out.println("✓ Payment successful via Google Pay\n");
            }
        }

        class OrderProcessor {
            private PaymentStrategy paymentStrategy;
            public void setPaymentStrategy(PaymentStrategy strategy) {
                this.paymentStrategy = strategy;
                System.out.println("Payment method set to: " + strategy.getClass().getSimpleName());
                System.out.println("-----------------------------------");
            }
            public void processPayment(double amount) {
                if (paymentStrategy == null) {
                    System.out.println("Payment strategy not set!");
                    return;
                }
                paymentStrategy.pay(amount);
            }
        }


        System.out.println("========== Strategy Pattern Demo ==========\n");
        OrderProcessor processor = new OrderProcessor();
        System.out.println("--- Strategy 1: Credit Card Payment ---");
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9012-3456", "John Doe", "123");
        processor.setPaymentStrategy(creditCard);
        processor.processPayment(150.00);

        System.out.println("--- Strategy 2: PayPal Payment ---");
        PaymentStrategy paypal = new PayPalPayment("john@example.com", "password123");
        processor.setPaymentStrategy(paypal);
        processor.processPayment(200.00);
        System.out.println("--- Strategy 3: Bitcoin Payment ---");
        PaymentStrategy bitcoin = new BitcoinPayment("1A1z7agoat2YLZW51Bc1P4JPc9cbky4eZN");
        processor.setPaymentStrategy(bitcoin);
        processor.processPayment(500.00);
        System.out.println("--- Strategy 4: Google Pay ---");
        PaymentStrategy googlePay = new GooglePayPayment("+1-555-0123");
        processor.setPaymentStrategy(googlePay);
        processor.processPayment(75.50);
        System.out.println("--- Switching Back to Credit Card ---");
        processor.setPaymentStrategy(creditCard);
        processor.processPayment(99.99);
        System.out.println("========== Strategy Pattern Complete ==========");
    }

}
