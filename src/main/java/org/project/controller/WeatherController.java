package org.project.controller;

import org.json.JSONObject;
import org.project.model.DailyForecast;
import org.project.model.IForecast;
import org.project.service.WeatherService;
import org.project.transformer.WeatherDataTransformer;
import org.project.utils.HtmlRenderer;
import org.project.utils.WeatherTableFormatter;
import org.project.utils.WeatherPlotFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private WeatherDataTransformer weatherDataTransformer;

    @Autowired
    private WeatherTableFormatter tableFormatter;

    @Autowired
    private WeatherPlotFormatter plotFormatter;

    @Autowired
    private HtmlRenderer htmlRenderer;

    @GetMapping("/")
    public String index() {
        return "forward:/";
    }

    @PostMapping("/getWeather")
    public String getWeather(@RequestParam("latitude") double latitude,
                             @RequestParam("longitude") double longitude,
                             Model model) {
        try {
            // Fetch location name
            String locationName = weatherService.getLocationName(latitude, longitude);

            // Fetch weather data
            String weatherData = weatherService.getWeatherData(latitude, longitude);
            List<IForecast> forecasts = weatherDataTransformer.transform(weatherData);

            // Fallback to lat/lon if location name is unavailable
            String location = locationName != null ? (locationName + "[" + latitude + ", " + longitude + "]") : ("[" + latitude + ", " + longitude + "]");

            String tableHtml = htmlRenderer.renderTable(tableFormatter.formatTable(forecasts));
            List<Integer> plotData = plotFormatter.formatPlot(forecasts);
            List<String> dates = forecasts.stream().map(f -> ((DailyForecast) f).getDate().toString()).toList();
            String plotHtml = htmlRenderer.renderPlot(plotData, dates);

            model.addAttribute("location", location);
            model.addAttribute("table", tableHtml);
            model.addAttribute("plot", plotHtml);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to fetch weather data: " + e.getMessage());
        }
        return "weather";
    }
}