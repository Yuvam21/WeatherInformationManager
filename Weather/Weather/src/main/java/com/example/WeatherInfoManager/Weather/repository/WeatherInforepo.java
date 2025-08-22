package com.example.WeatherInfoManager.Weather.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeatherInfoManager.Weather.model.WeatherInfo;

public interface WeatherInforepo extends JpaRepository<WeatherInfo, Long>
{
	Optional<WeatherInfo> findByPincodeAndDate(String pincode, LocalDate date);
}
