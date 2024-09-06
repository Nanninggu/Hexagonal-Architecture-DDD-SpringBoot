package com.example.saga_pattern;

import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SagaPatternTest {

    @Autowired
    private UserService userService;

    @Test
    public void testCreateMultipleUsers() {
        for (long i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("User" + i);
            user.setEmail("user" + i + "@example.com");
            try {
                userService.createUser(user);
            } catch (Exception e) {
                // Handle exception if needed
            }
        }
    }
}