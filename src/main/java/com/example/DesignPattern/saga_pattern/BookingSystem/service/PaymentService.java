package com.example.DesignPattern.saga_pattern.BookingSystem.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean processPayment(String paymentDetails) {
        // Implement payment processing logic here
        // Return true if payment is successful, false otherwise
        return true;
    }

    public void refundPayment(String paymentDetails) {
        // Implement refund logic here
    }
}
