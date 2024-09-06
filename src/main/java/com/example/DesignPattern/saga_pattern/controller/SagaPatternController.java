package com.example.DesignPattern.saga_pattern.controller;

import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.saga.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SagaPatternController {

    @Autowired
    private Saga<User> saga;

    @GetMapping("/saga_pattern")
    public String getSagaPatternPage() {
        return "saga_pattern";
    }

    @PostMapping("/test-rollback")
    public void testRollback() {
        User user = new User(); // Create a test user
        saga.execute(user);
        saga.rollback(user);
    }

    @PostMapping("/test-transaction")
    public String testTransaction() {
        User user = new User(); // Create a test user
        user.setName("Test User");
        user.setEmail("Test User@Email.com");
        saga.execute(user);
        return "saga_pattern";
    }
}
