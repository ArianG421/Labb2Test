package com.example.payment;

public class PaymentProcessor {
    private final String apiKey;
    private final PaymentApi paymentApi;
    private final DatabaseConnection databaseConnection;
    private final EmailService emailService;

    public PaymentProcessor(String apiKey, PaymentApi paymentApi, DatabaseConnection databaseConnection, EmailService emailService) {
        this.apiKey = apiKey;
        this.paymentApi = paymentApi;
        this.databaseConnection = databaseConnection;
        this.emailService = emailService;
    }

    public boolean processPayment(double amount) {
        // Anropar extern betaltjänst via PaymentApi-interface
        PaymentApiResponse response = paymentApi.charge(apiKey, amount);

        // Skriver till databas via DatabaseConnection-interface
        if (response.isSuccess()) {
            databaseConnection.executeUpdate("INSERT INTO payments (amount, status) VALUES (" + amount + ", 'SUCCESS')");
        }

        // Skickar e-post via EmailService-interface
        if (response.isSuccess()) {
            emailService.sendPaymentConfirmation("user@example.com", amount);
        }

        return response.isSuccess();
    }
}