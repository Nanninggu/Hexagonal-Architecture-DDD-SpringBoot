package com.example.DesignPattern.saga_pattern.controller;

import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/initializeSaga")
    public String initializeSaga() {
        userService.initializeSaga();
        return "Saga initialized and executed once";
    }
}
