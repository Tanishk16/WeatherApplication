package com.tanishk.weatherapplication.Controller;

import com.tanishk.weatherapplication.Response.WeatherResponse;
import com.tanishk.weatherapplication.Service.weatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
    private final weatherService weatherService;

    public WeatherController(weatherService weatherService){
        this.weatherService = weatherService;
    }
    @GetMapping("/weather/current")
    public ResponseEntity<?> getCurrentWeather(@RequestParam String city){
        try {
            String weatherData = weatherService.getCurrentWeather(city);
            return ResponseEntity.ok(weatherData);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/weather/parsed")
    public WeatherResponse getParsedWeather(@RequestParam String city){
        return weatherService.getParsedWeather(city);
    }
}
