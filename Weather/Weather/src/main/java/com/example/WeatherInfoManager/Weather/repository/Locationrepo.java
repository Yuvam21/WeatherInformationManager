package com.example.WeatherInfoManager.Weather.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WeatherInfoManager.Weather.model.Location;

public interface Locationrepo extends JpaRepository<Location, Long>
{
	Optional<Location> findByPincode(String pincode);
}
