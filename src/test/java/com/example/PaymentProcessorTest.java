package com.example;

import com.example.payment.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentProcessorTest {

    @Test
    void testProcessPayment_Success() {
        // Skapa mock-objekt för beroenden
        PaymentApi paymentApi = Mockito.mock(PaymentApi.class);
        DatabaseConnection databaseConnection = Mockito.mock(DatabaseConnection.class);
        EmailService emailService = Mockito.mock(EmailService.class);

        // Skapa ett lyckat svar från PaymentApi
        PaymentApiResponse successResponse = new PaymentApiResponse(true);
        when(paymentApi.charge(anyString(), anyDouble())).thenReturn(successResponse);

        // Skapa en instans av PaymentProcessor med mock-objekt
        PaymentProcessor processor = new PaymentProcessor("sk_test_123456", paymentApi, databaseConnection, emailService);

        // Testa processPayment-metoden
        boolean result = processor.processPayment(100.0);

        // Verifiera att processPayment returnerar true
        assertTrue(result);

        // Verifiera att beroenden anropas korrekt
        verify(paymentApi).charge("sk_test_123456", 100.0);
        verify(databaseConnection).executeUpdate("INSERT INTO payments (amount, status) VALUES (100.0, 'SUCCESS')");
        verify(emailService).sendPaymentConfirmation("user@example.com", 100.0);
    }

    @Test
    void testProcessPayment_Failure() {
        // Skapa mock-objekt för beroenden
        PaymentApi paymentApi = Mockito.mock(PaymentApi.class);
        DatabaseConnection databaseConnection = Mockito.mock(DatabaseConnection.class);
        EmailService emailService = Mockito.mock(EmailService.class);

        // Skapa ett misslyckat svar från PaymentApi
        PaymentApiResponse failureResponse = new PaymentApiResponse(false);
        when(paymentApi.charge(anyString(), anyDouble())).thenReturn(failureResponse);

        // Skapa en instans av PaymentProcessor med mock-objekt
        PaymentProcessor processor = new PaymentProcessor("sk_test_123456", paymentApi, databaseConnection, emailService);

        // Testa processPayment-metoden
        boolean result = processor.processPayment(100.0);

        // Verifiera att processPayment returnerar false
        assertFalse(result);

        // Verifiera att beroenden inte anropas vid misslyckad betalning
        verify(paymentApi).charge("sk_test_123456", 100.0);
        verify(databaseConnection, never()).executeUpdate(anyString());
        verify(emailService, never()).sendPaymentConfirmation(anyString(), anyDouble());
    }
}