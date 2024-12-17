package com.tanishk.weatherapplication.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

public class WeatherResponse {
    private String city;
    private String description;
    private double temperature;
    private int humidity;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
