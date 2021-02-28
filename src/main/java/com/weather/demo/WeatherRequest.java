package com.weather.demo;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

public class WeatherRequest {
    @Getter @Setter private Date date;
    @Getter private int minTemp;
    @Getter private int maxTemp;
    @Getter private String weatherCondition;

    public WeatherRequest(Date date, int temp_min, int temp_max, String weatherCondition) {

        this.date = date;
        this.minTemp = kelvinToCelsius(temp_min);
        this.maxTemp = kelvinToCelsius(temp_max);
        this.weatherCondition = weatherCondition;
    }

    private int kelvinToCelsius(int temp) {
        return temp - 273;
    }

    public String getMessage() {
        if ("Rain".equalsIgnoreCase(weatherCondition)) {
            return "Carry umbrella";
        } else if (maxTemp > 40) {
            return "Use sunscreen lotion";
        }

        return "";
    }
}
