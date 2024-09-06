package com.example.DesignPattern.saga_pattern.BookingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingPageController {

    @GetMapping("/booking")
    public String bookingPage() {
        return "booking";
    }
}
