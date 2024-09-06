package com.example.DesignPattern.saga_pattern.saga;

import com.example.DesignPattern.saga_pattern.handler.LogWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Saga<T> {
    private static final Logger logger = LoggerFactory.getLogger(Saga.class);
    private final List<SagaStep<T>> steps = new ArrayList<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Saga<T> addStep(SagaStep<T> step) {
        steps.add(step);
        return this;
    }

    public void startPeriodicExecution(T data, long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(() -> execute(data), initialDelay, period, unit);
        scheduler.scheduleAtFixedRate(() -> rollback(data), initialDelay + period / 2, period, unit);
    }

    public void execute(T data) {
        try {
            logger.info("Starting saga execution");
            LogWebSocketHandler.broadcast("Starting saga execution");
            for (SagaStep<T> step : steps) {
                String stepInfo = "Executing step: " + step.getClass().getSimpleName() + ".execute";
                logger.info(stepInfo);
                LogWebSocketHandler.broadcast(stepInfo);
                step.execute(data);
            }
            logger.info("Saga execution completed");
            LogWebSocketHandler.broadcast("Saga execution completed");
        } catch (Exception e) {
            rollback(data);
        }
    }

    public void rollback(T data) {
        try {
            logger.info("Starting saga rollback");
            LogWebSocketHandler.broadcast("Starting saga rollback");
            for (int i = steps.size() - 1; i >= 0; i--) {
                String stepInfo = "Rolling back step: " + steps.get(i).getClass().getSimpleName() + ".rollback";
                logger.info(stepInfo);
                LogWebSocketHandler.broadcast(stepInfo);
                steps.get(i).rollback(data);
            }
            logger.info("Saga rollback completed");
            LogWebSocketHandler.broadcast("Saga rollback completed");
        } catch (Exception e) {
            logger.error("Error during rollback", e);
        }
    }
}
