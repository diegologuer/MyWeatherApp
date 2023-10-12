package com.climate.MyWeatherApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.climate.MyWeatherApp.model.WeatherData;
import com.climate.MyWeatherApp.service.WeatherService;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    // Endpoint to fetch weather data by city name
    @GetMapping("/byCity")
    public ResponseEntity<?> getWeatherByCity(
            @RequestParam String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String country) {
        try {
            WeatherData weatherData = weatherService.fetchWeatherByCity(city, state, country);
            return ResponseEntity.ok(weatherData);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        }
    }

    // Endpoint to fetch weather data by postal code
    @GetMapping("/byPostalCode")
    public ResponseEntity<?> getWeatherByPostalCode(
            @RequestParam String postalCode,
            @RequestParam(required = false) String country) {
        try {
            WeatherData weatherData = weatherService.fetchWeatherByPostalCode(postalCode);
            return ResponseEntity.ok(weatherData);
        } catch (WeatherService.WeatherApiException ex) {
            return ResponseEntity.status(500).body("Error fetching weather data: " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Invalid request: " + ex.getMessage());
        }
    }
}




