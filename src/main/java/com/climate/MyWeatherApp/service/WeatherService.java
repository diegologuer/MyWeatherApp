package com.climate.MyWeatherApp.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.climate.MyWeatherApp.api.WeatherApiResponse;
import com.climate.MyWeatherApp.model.WeatherData;
import com.climate.MyWeatherApp.repository.WeatherDataRepository;
import com.climate.MyWeatherApp.service.WeatherService.WeatherApiException;





@Service
public class WeatherService {
    public class WeatherApiException extends Exception {

	}

	@Autowired
    private WeatherDataRepository dataRepository;

    @Value("${weather.api.key}")
    private String apiKey;

    // Create a RestTemplate bean to make API requests
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    // Method to fetch weather data by city name
    public WeatherData fetchWeatherByCity(String city, String state, String country) {
        // Construct the API URL
        String apiUrl = "https://api.weatherbit.io/v2.0/current?city=" + city;
        if (state != null && !state.isEmpty()) {
            apiUrl += "," + state;
        }
        if (country != null && !country.isEmpty()) {
            apiUrl += "&country=" + country;
        }
        apiUrl += "&key=" + apiKey;

        // Make the API request
        ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(apiUrl, WeatherApiResponse.class);

        // Extract and save the relevant data to the database
        WeatherData weatherData = saveWeatherData(response.getBody().getData().get(0));
        return weatherData;
    }
    
    public WeatherData fetchWeatherByPostalCode(String postalCode) throws WeatherApiException {
        try {
            // Validate input parameters
            if (postalCode == null || postalCode.isEmpty()) {
                throw new IllegalArgumentException("Postal code is required.");
            }

            // Construct the API URL for fetching by postal code
            String apiUrl = "https://api.weatherbit.io/v2.0/current?postal_code=" + postalCode;
            
            apiUrl += "&key=" + apiKey;

            // Make the API request
            ResponseEntity<WeatherApiResponse> response = restTemplate.getForEntity(apiUrl, WeatherApiResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                WeatherData weatherData = saveWeatherData(response.getBody().getData().get(0));
                return weatherData;
            } else {
                throw new WeatherApiException();
            }
        } catch (RestClientException | WeatherApiException ex) {
            // Log the exception
           
            throw new WeatherApiException();
        }
    }


    // Method to save weather data to the database
    private WeatherData saveWeatherData(WeatherData data) {
        return dataRepository.save(data);
    }

    // Implement scheduling for periodic data refresh
    @Scheduled(fixedRate = 3600000) // Example: Refresh data every hour
    public void refreshWeatherData() {
        // Implement logic to periodically refresh data here
    }
}




