package com.example.DesignPattern.saga_pattern.service;

import com.example.DesignPattern.saga_pattern.component.CreateUserStep;
import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.saga.Saga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private CreateUserStep createUserStep;

    private Saga<User> saga;

    public synchronized void initializeSaga() {
        saga = new Saga<User>().addStep(createUserStep);
        saga.startPeriodicExecution(new User(), 0, Long.MAX_VALUE, TimeUnit.DAYS); // Execute once
        logger.info("Saga initialized and executed once");
    }

    public void createUser(User user) {
        logger.info("Starting user creation saga for: {}", user);
        try {
            saga.execute(user);
            logger.info("User creation saga executed successfully for: {}", user);
        } catch (Exception e) {
            logger.error("Error during user creation saga execution for: {}", user, e);
            saga.rollback(user);
            logger.info("User creation saga rolled back for: {}", user);
            throw e;
        }
    }
}