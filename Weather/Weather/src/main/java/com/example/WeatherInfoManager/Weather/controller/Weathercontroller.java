package com.example.WeatherInfoManager.Weather.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WeatherInfoManager.Weather.model.WeatherInfo;
import com.example.WeatherInfoManager.Weather.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class Weathercontroller
{
	@Autowired
	private WeatherService weatherService;
	
	@GetMapping
    public WeatherInfo getWeather(
    		@RequestParam String pincode,
    		@RequestParam("for_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate forDate)
	{
        return weatherService.getWeather(pincode, forDate);
    }
	
}
