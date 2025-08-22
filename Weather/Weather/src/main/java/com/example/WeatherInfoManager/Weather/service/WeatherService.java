package com.example.WeatherInfoManager.Weather.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.WeatherInfoManager.Weather.exception.InvalidDateException;
import com.example.WeatherInfoManager.Weather.exception.InvalidPincodeException;
import com.example.WeatherInfoManager.Weather.model.Location;
import com.example.WeatherInfoManager.Weather.model.WeatherInfo;
import com.example.WeatherInfoManager.Weather.repository.Locationrepo;
import com.example.WeatherInfoManager.Weather.repository.WeatherInforepo;

@Service
public class WeatherService
{
	@Autowired
	private Locationrepo locationRepo;
	@Autowired
    private WeatherInforepo weatherRepo;
    
    private String GEO_API = "http://api.openweathermap.org/geo/1.0/zip?zip={zip},IN&appid={key}";
    private String WEATHER_API = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={key}&units=metric";
    
    private String API_KEY = "642a44b28136eb55e63e497ac7965704";
    
    public WeatherInfo getWeather(String pincode, LocalDate date)
    {
    	if (pincode == null || !pincode.matches("^[1-9][0-9]{5}$")) 
    	{
            throw new InvalidPincodeException("Invalid pincode: " + pincode);
        }
    	
    	if (date.isAfter(LocalDate.now())) {
            throw new InvalidDateException("Weather data not available for future date: " + date);
        }
    	
    	Optional<WeatherInfo> existing = weatherRepo.findByPincodeAndDate(pincode, date);
    	
    	if (existing.isPresent()) 
    	{
            return existing.get(); 
        }
          
    	RestTemplate restTemplate = new RestTemplate();
    	
            Location location = locationRepo.findByPincode(pincode)
                    .orElseGet(() -> {
                        Map<String, Object> geoResp = restTemplate.getForObject(
                                GEO_API, Map.class, Map.of("zip", pincode, "key", API_KEY));

                        Double lat = (Double) geoResp.get("lat");
                        Double lon = (Double) geoResp.get("lon");

                        Location newLoc = new Location(null, pincode, lat, lon);
                        return locationRepo.save(newLoc);
                    });
          
            Map<String, Object> weatherResp = restTemplate.getForObject(
                    WEATHER_API, Map.class,
                    Map.of("lat", location.getLatitude(), "lon", location.getLongitude(), "key", API_KEY));

            Map<String, Object> main = (Map<String, Object>) weatherResp.get("main");
            Map<String, Object> weather = ((java.util.List<Map<String, Object>>) weatherResp.get("weather")).get(0);

            WeatherInfo info = new WeatherInfo();
            info.setPincode(pincode);
            info.setDate(date);
            info.setDescription((String) weather.get("description"));
            info.setTemperature(((Number) main.get("temp")).doubleValue());
            info.setFeelsLike(((Number) main.get("feels_like")).doubleValue());
            info.setHumidity(((Number) main.get("humidity")).doubleValue());
            
            WeatherInfo saved = weatherRepo.save(info);

       
            return saved;
    }
}
