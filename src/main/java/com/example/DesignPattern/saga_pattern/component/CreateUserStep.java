package com.example.DesignPattern.saga_pattern.component;

import com.example.DesignPattern.saga_pattern.dto.User;
import com.example.DesignPattern.saga_pattern.mapper.UserMapper;
import com.example.DesignPattern.saga_pattern.saga.SagaStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserStep implements SagaStep<User> {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void execute(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void rollback(User user) {
        userMapper.deleteUser(user.getId());
    }
}
