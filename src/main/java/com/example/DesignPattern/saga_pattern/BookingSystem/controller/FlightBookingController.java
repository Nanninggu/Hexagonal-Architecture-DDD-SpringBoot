package com.example.DesignPattern.saga_pattern.BookingSystem.controller;

import com.example.DesignPattern.saga_pattern.BookingSystem.dto.FlightBooking;
import com.example.DesignPattern.saga_pattern.BookingSystem.dto.FlightBookingRequest;
import com.example.DesignPattern.saga_pattern.BookingSystem.saga.BookingSagaOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightBookingController {

    @Autowired
    private BookingSagaOrchestrator bookingSagaOrchestrator;

    @PostMapping("/book")
    public FlightBooking bookFlight(@RequestBody FlightBookingRequest request) {
        try {
            return bookingSagaOrchestrator.bookFlight(request);
        } catch (Exception e) {
            bookingSagaOrchestrator.rollback(request);
            throw e;
        }
    }
}
