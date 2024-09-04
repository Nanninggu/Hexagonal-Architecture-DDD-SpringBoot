package com.example.infrastructure;

import com.example.domain.Entity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EntityMapper {

    List<Entity> findAll();

    Entity findById(Long id);

    void save(Entity entity);

    void deleteById(Long id);
}
