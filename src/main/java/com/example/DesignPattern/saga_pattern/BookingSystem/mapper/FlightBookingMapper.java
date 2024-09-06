package com.example.DesignPattern.saga_pattern.BookingSystem.mapper;

import com.example.DesignPattern.saga_pattern.BookingSystem.dto.FlightBooking;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlightBookingMapper {
    void insertFlightBooking(FlightBooking booking);
}
