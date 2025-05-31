package org.project.service;

import org.project.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        this.restTemplate = new RestTemplate();
    }

    public String getWeatherData(double latitude, double longitude) {
        String url = String.format(
            "https://api.openweathermap.org/data/3.0/onecall?lat=%f&lon=%f&exclude=current,minutely,hourly,alerts&appid=%s",
            latitude, longitude, apiConfig.getApiKey()
        );
        return restTemplate.getForObject(url, String.class);
    }
}
