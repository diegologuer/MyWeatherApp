package com.climate.MyWeatherApp.api;

public class WeatherApiException extends RuntimeException {
    public WeatherApiException(String message) {
        super(message);
    }

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }
}

