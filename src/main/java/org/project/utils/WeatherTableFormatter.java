package org.project.utils;

import org.project.model.IForecast;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherTableFormatter {
    public List<List<String>> formatTable(List<IForecast> forecasts) {
        List<List<String>> table = new ArrayList<>();
        table.add(List.of("Date", "Summary", "Humidity", "Wind Speed", "Rain", "POP", "Rain Animation"));

        for (IForecast forecast : forecasts) {
            String rainAnimation = forecast.getRain() > 1 ? "☔☔☔" : forecast.getRain() > 0.5 ? "☔☔" : "☔";
            table.add(List.of(
                    forecast.getDate().toString(),
                    forecast.getSummary(),
                    String.valueOf(forecast.getHumidity()),
                    String.valueOf(forecast.getWindSpeed()),
                    String.valueOf(forecast.getRain()),
                    String.valueOf(forecast.getPop()),
                    rainAnimation
            ));
        }
        return table;
    }
}