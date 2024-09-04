package com.example.application;

import com.example.domain.Entity;

import java.util.List;

public interface EntityService {
    List<Entity> findAll();

    Entity findById(Long id);

    void save(Entity entity);

    void deleteById(Long id);
}
