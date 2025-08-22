package com.example.WeatherInfoManager.Weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.WeatherInfoManager.Weather.exception.InvalidDateException;
import com.example.WeatherInfoManager.Weather.exception.InvalidPincodeException;
import com.example.WeatherInfoManager.Weather.model.WeatherInfo;
import com.example.WeatherInfoManager.Weather.repository.Locationrepo;
import com.example.WeatherInfoManager.Weather.repository.WeatherInforepo;
import com.example.WeatherInfoManager.Weather.service.WeatherService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class WeatherTest 
{
	@Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherInforepo weatherRepo;

    @Autowired
    private Locationrepo locationRepo;

    @Autowired
    private Locationrepo pincodeLocationRepo;
    
    @Test
    void testValidPincodeAndDate_SavesWeatherAndLocation() {
        String pincode = "110018";
        LocalDate date = LocalDate.now();

        WeatherInfo info = weatherService.getWeather(pincode, date);

        assertNotNull(info);
        assertEquals(pincode, info.getPincode());
        assertTrue(info.getTemperature() > -50 && info.getTemperature() < 60); // rough sanity check

        assertTrue(locationRepo.findByPincode(pincode).isPresent());
        assertFalse(pincodeLocationRepo.findAll().isEmpty());
    }
    
    @Test
    void testMultiplePincodeLocationEntries() {
        String pincode = "110001";
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().minusDays(1);

        weatherService.getWeather(pincode, date1);
        weatherService.getWeather(pincode, date2);

        
        assertTrue(pincodeLocationRepo.findAll().size() >= 2);
    }
    
    @Test
    void testNullPincode_ThrowsException()
    {
        LocalDate date = LocalDate.now();
        assertThrows(InvalidPincodeException.class, () -> weatherService.getWeather(null, date));
    }
    
    @Test
    void testEmptyPincode_ThrowsException() {
        LocalDate date = LocalDate.now();
        assertThrows(InvalidPincodeException.class, () -> weatherService.getWeather("", date));
    }
    
    @Test
    void testFutureDate_ThrowsException() {
        String pincode = "110001";
        LocalDate futureDate = LocalDate.now().plusDays(1);

        InvalidDateException ex = assertThrows(InvalidDateException.class, () -> 
            weatherService.getWeather(pincode, futureDate)
        );

        assertTrue(ex.getMessage().contains("Weather data not available for future date"));
    }
}
