package com.tanishk.weatherapplication.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class weatherService {
    @Value("${weather.api.key}")
    private String apikey;

    private final RestTemplate restTemplate;

    public weatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getCurrentWeather(String city){
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apikey);
        try{
            return restTemplate.getForObject(url, String.class);
        }
        catch (Exception e){
            log.error("Error Fetching weather data: " + e);
            throw new RuntimeException("City Not Found or API error");
        }
    }
}
