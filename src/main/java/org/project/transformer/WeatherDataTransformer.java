package org.project.transformer;

import org.json.JSONArray;
import org.json.JSONObject;
import org.project.model.DailyForecast;
import org.project.model.IForecast;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherDataTransformer {

    public List<IForecast> transform(String jsonResponse) {
        List<IForecast> forecasts = new ArrayList<>();
        JSONObject response = new JSONObject(jsonResponse);
        JSONArray dailyForecasts = response.getJSONArray("daily");

        for (int i = 0; i < dailyForecasts.length(); i++) {
            JSONObject day = dailyForecasts.getJSONObject(i);
            long timestamp = day.getLong("dt");
            LocalDate date = Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
            String summary = day.optString("summary", "No summary available");
            double humidity = day.optDouble("humidity", 0);
            double windSpeed = day.optDouble("wind_speed", 0);
            double pop = day.optDouble("pop", 0);
            double rain = day.optDouble("rain", 0);

            forecasts.add(new DailyForecast(date, summary, humidity, windSpeed, pop, rain));
        }

        return forecasts;
    }
}