package org.project.service;

import org.project.config.ApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Lazy;
import org.json.JSONArray;

/**
 * Service to interact with the OpenWeather One Call API.
 * <p>
 * Parameters that can be used in the API:
 * - `lat` (latitude): Geographic coordinate of the location.
 * - `lon` (longitude): Geographic coordinate of the location.
 * - `exclude`: Data to exclude (e.g., current, minutely, hourly, alerts).
 * - `appid`: API key for authentication.
 * - Additional optional parameters:
 *   - `units`: Units of measurement (standard, metric, imperial).
 *   - `lang`: Language for the response (e.g., en, es, fr).
 */
@Service
public class WeatherService {

    private final ApiConfig apiConfig;
    private final RestTemplate restTemplate;

    @Autowired
    public WeatherService(ApiConfig apiConfig, @Lazy RestTemplate restTemplate) {
        this.apiConfig = apiConfig;
        this.restTemplate = restTemplate;
    }

    public String getWeatherData(double latitude, double longitude) {
        String url = apiConfig.getApiUrl() +
                "?lat=" + latitude +
                "&lon=" + longitude +
                "&exclude=current,minutely,hourly,alerts" +
                "&appid=" + apiConfig.getApiKey();
        //System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }

    public String getLocationName(double latitude, double longitude) {
        String url = "http://api.openweathermap.org/geo/1.0/reverse" +
                "?lat=" + latitude +
                "&lon=" + longitude +
                "&limit=1" +
                "&appid=" + apiConfig.getApiKey();
        String response = restTemplate.getForObject(url, String.class);
        JSONArray jsonArray = new JSONArray(response);
        if (jsonArray.length() > 0) {
            return jsonArray.getJSONObject(0).optString("name", null);
        }
        return null;
    }
}
