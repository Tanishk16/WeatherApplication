package com.tanishk.weatherapplication.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanishk.weatherapplication.Response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class weatherService {
    @Value("${weather.api.key}")
    private String apikey;
    private final RestTemplate restTemplate;
    private static final Logger Logger = LoggerFactory.getLogger(weatherService.class);

    public weatherService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public String getCurrentWeather(String city){
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city, apikey);
        try{
            return restTemplate.getForObject(url, String.class);
        }
        catch (Exception e){
            throw new RuntimeException("City Not Found or API error");
        }
    }
    public WeatherResponse getParsedWeather(String city){
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",city,apikey);
        try{
            String response = restTemplate.getForObject(url, String.class);
            // It is used to convert java object into json or visa versa
            ObjectMapper mapper = new ObjectMapper();
            WeatherResponse weatherResponse = new WeatherResponse();
            // It reads the raw json(response) and converts it into a tree structure
            JsonNode root = mapper.readTree(response);
            weatherResponse.setCity(root.get("name").asText());
            weatherResponse.setDescription(root.get("weather").get(0).get("description").asText());
            weatherResponse.setTemperature(root.get("main").get("temp").asDouble());
            weatherResponse.setHumidity(root.get("main").get("humidity").asInt());
            return weatherResponse;
        }
        catch (Exception e){
            Logger.error("Error Parsing weather data {}",e.getMessage(),e);
            throw new RuntimeException("Error processing weather data.");
        }
    }
}
