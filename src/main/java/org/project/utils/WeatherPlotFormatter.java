package org.project.utils;

import org.project.model.IForecast;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WeatherPlotFormatter {
    public List<Integer> formatPlot(List<IForecast> forecasts) {
        List<Integer> plotData = new ArrayList<>();
        for (IForecast forecast : forecasts) {
            int popScale = (int) (forecast.getPop() * 100); // Scale `pop` for plotting
            plotData.add(popScale);
        }
        return plotData;
    }
}