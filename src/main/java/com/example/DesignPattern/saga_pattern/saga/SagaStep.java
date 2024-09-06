package com.example.DesignPattern.saga_pattern.saga;

public interface SagaStep<T> {
    void execute(T data);
    void rollback(T data);
}
