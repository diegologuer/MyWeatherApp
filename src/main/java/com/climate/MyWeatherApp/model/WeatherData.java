package com.climate.MyWeatherApp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeatherData{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String state_code;
    private String country_code;
    private String postal_code;
    private String city_name;
    private LocalDateTime datetime;
    private double temp;
    private int clouds;
    private double precip;
    
    
    
	public WeatherData() {
		super();
	}

	public WeatherData(Long id, String state_code, String country_code, String postal_code, String city_name, LocalDateTime datetime,
			double temp, int clouds, double precip) {
		super();
		this.id = id;
		this.state_code = state_code;
		this.country_code = country_code;
		this.postal_code = postal_code;
		this.city_name = city_name;
		this.datetime = datetime;
		this.temp = temp;
		this.clouds = clouds;
		this.precip = precip;
	}

	
	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getState_code() {
		return state_code;
	}

	public void setState_code(String state_code) {
		this.state_code = state_code;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public LocalDateTime getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	public double getPrecip() {
		return precip;
	}

	public void setPrecip(double precip) {
		this.precip = precip;
	}
	
	
	
	
    
	
	
    
    

    // Other fields and getters and setters
}
