package com.climate.MyWeatherApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.climate.MyWeatherApp.model.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    // Add custom query methods if needed
}

