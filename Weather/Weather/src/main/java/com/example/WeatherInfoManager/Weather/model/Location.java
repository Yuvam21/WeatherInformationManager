package com.example.WeatherInfoManager.Weather.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class Location 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pincode;
    private Double latitude;
    private Double longitude;
    
    public Location()
    {
    	
    }

    public Location(Long id, String pincode, Double latitude, Double longitude)
    {
        this.id = id;
        this.pincode = pincode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
}
