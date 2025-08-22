package com.example.WeatherInfoManager.Weather.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "weather_info")
public class WeatherInfo
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pincode;
    private LocalDate date;
    private String description;
    private Double temperature;
    private Double feelsLike;
    private Double humidity;
    
    public WeatherInfo() {}
    
    public WeatherInfo(Long id, String pincode, LocalDate date, 
            String description, Double temperature, 
            Double feelsLike, Double humidity) 
    {
    	this.id = id;
        this.pincode = pincode;
        this.date = date;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTemperature() {
        return temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }
    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getHumidity() {
        return humidity;
    }
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

	@Override
	public String toString() {
		return "WeatherInfo [id=" + id + ", pincode=" + pincode + ", date=" + date + ", description=" + description
				+ ", temperature=" + temperature + ", feelsLike=" + feelsLike + ", humidity=" + humidity + "]";
	}
    
    

}