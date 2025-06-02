package org.project.model;

import java.time.LocalDate;

public interface IForecast {
    LocalDate getDate();
    String getSummary();
    double getHumidity();
    double getWindSpeed();
    double getPop();
    double getRain();
}
