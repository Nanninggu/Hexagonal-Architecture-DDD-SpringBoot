package com.example.application;

import com.example.domain.TouristSpot;
import com.example.infrastructure.TouristSpotMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristSpotService {
    private final TouristSpotMapper touristSpotMapper;

    public TouristSpotService(TouristSpotMapper touristSpotMapper) {
        this.touristSpotMapper = touristSpotMapper;
    }

    public List<TouristSpot> getSeoulTouristSpots() {
        return touristSpotMapper.findSeoulTouristSpots();
    }
}
