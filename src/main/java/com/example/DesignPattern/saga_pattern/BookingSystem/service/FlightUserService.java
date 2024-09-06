package com.example.DesignPattern.saga_pattern.BookingSystem.service;

import org.springframework.stereotype.Service;

@Service("flightUserService")
public class FlightUserService {
    public boolean validateUser(String userId) {
        // Validate user logic
        return true;
    }
}
