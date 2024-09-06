package com.example.DesignPattern.saga_pattern.BookingSystem.saga;

import com.example.DesignPattern.saga_pattern.BookingSystem.dto.FlightBooking;
import com.example.DesignPattern.saga_pattern.BookingSystem.dto.FlightBookingRequest;
import com.example.DesignPattern.saga_pattern.BookingSystem.mapper.FlightBookingMapper;
import com.example.DesignPattern.saga_pattern.BookingSystem.service.FlightService;
import com.example.DesignPattern.saga_pattern.BookingSystem.service.PaymentService;
import com.example.DesignPattern.saga_pattern.BookingSystem.service.SeatService;
import com.example.DesignPattern.saga_pattern.BookingSystem.service.FlightUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookingSagaOrchestrator {

    @Autowired
    @Qualifier("flightUserService")
    private FlightUserService flightUserService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private FlightBookingMapper flightBookingMapper;

    public FlightBooking bookFlight(FlightBookingRequest request) {
        if (!flightUserService.validateUser(request.getUserId())) {
            throw new RuntimeException("Invalid user");
        }

        if (!flightService.validateFlight(request.getFlightId())) {
            throw new RuntimeException("Invalid flight");
        }

        if (!seatService.reserveSeat(request.getFlightId(), request.getSeatClass(), request.getSeatNumber())) {
            throw new RuntimeException("Seat reservation failed");
        }

        if (!paymentService.processPayment(request.getPaymentDetails())) {
            throw new RuntimeException("Payment failed");
        }

        FlightBooking booking = new FlightBooking();
        booking.setUserId(request.getUserId());
        booking.setFlightId(request.getFlightId());
        booking.setSeatClass(request.getSeatClass());
        booking.setSeatNumber(request.getSeatNumber()); // Ensure seatNumber is set
        booking.setStatus("BOOKED");
        booking.setPaymentStatus("PAID");

        flightBookingMapper.insertFlightBooking(booking);
        return booking;
    }

    public void rollback(FlightBookingRequest request) {
        seatService.releaseSeat(request.getFlightId(), request.getSeatClass(), request.getSeatNumber());
        paymentService.refundPayment(request.getPaymentDetails());
        // Additional rollback logic if needed
    }
}