package com.climate.MyWeatherApp.api;

import com.climate.MyWeatherApp.model.WeatherData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherApiResponse {
    @JsonProperty("data")
    private List<WeatherData> data;

    public List<WeatherData> getData() {
        return data;
    }

    public void setData(List<WeatherData> data) {
        this.data = data;
    }
}
