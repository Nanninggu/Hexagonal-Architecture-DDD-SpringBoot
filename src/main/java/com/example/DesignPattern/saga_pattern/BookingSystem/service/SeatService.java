package com.example.DesignPattern.saga_pattern.BookingSystem.service;

import org.springframework.stereotype.Service;

@Service
public class SeatService {
    public boolean reserveSeat(String flightId, String seatClass, String seatNumber) {
        // Reserve seat logic
        return true;
    }

    public void releaseSeat(String flightId, String seatClass, String seatNumber) {
        // Release seat logic
    }
}
