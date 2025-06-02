package org.project.model;

import java.time.LocalDate;

public class DailyForecast implements IForecast {
    private LocalDate date;
    private String summary;
    private double humidity;
    private double windSpeed;
    private double pop; // Probability of precipitation
    private double rain;

    public DailyForecast(LocalDate date, String summary, double humidity, double windSpeed, double pop, double rain) {
        this.date = date;
        this.summary = summary;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.pop = pop;
        this.rain = rain;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getSummary() {
        return summary;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getPop() {
        return pop;
    }

    public double getRain() {
        return rain;
    }

    @Override
    public String toString() {
        return "Date: " + date +
                ", Summary: " + summary +
                ", Humidity: " + humidity + "%" +
                ", Wind Speed: " + windSpeed + " m/s" +
                ", Chance of Rain: " + (pop * 100) + "%" +
                ", Rain Volume: " + rain + " mm";
    }
}