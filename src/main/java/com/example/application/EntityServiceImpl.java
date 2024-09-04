package com.example.application;

import com.example.domain.Entity;
import com.example.infrastructure.EntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {
    private final EntityMapper entityMapper;

    public EntityServiceImpl(EntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @Override
    public List<Entity> findAll() {
        return entityMapper.findAll();
    }

    @Override
    public Entity findById(Long id) {
        return entityMapper.findById(id);
    }

    @Override
    public void save(Entity entity) {
        entityMapper.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        entityMapper.deleteById(id);
    }
}
