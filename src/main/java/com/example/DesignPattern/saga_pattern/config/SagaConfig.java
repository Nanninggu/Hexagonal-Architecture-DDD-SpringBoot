package com.example.DesignPattern.saga_pattern.config;

import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.saga.Saga;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagaConfig {

    @Bean
    public Saga<User> saga() {
        Saga<User> saga = new Saga<>();

        return saga;
    }
}
