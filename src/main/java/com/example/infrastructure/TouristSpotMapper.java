package com.example.infrastructure;

import com.example.domain.TouristSpot;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TouristSpotMapper {
    @Select("SELECT * FROM tourist_spot WHERE city = 'Seoul'")
    List<TouristSpot> findSeoulTouristSpots();
}
