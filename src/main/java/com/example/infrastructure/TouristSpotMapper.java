package com.example.infrastructure;

import com.example.domain.TouristSpot;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TouristSpotMapper {
    List<TouristSpot> findSeoulTouristSpots();
}