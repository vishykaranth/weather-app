package com.weather.demo;

import java.util.Date;

public class WeatherRequest {
    private Date date;
    private int temp_min;
    private int temp_max;
    private String weatherCondition;

    public WeatherRequest(Date date, int temp_min, int temp_max, String weatherCondition) {

        this.date = date;
        this.temp_min = kelvinToCelsius(temp_min);
        this.temp_max = kelvinToCelsius(temp_max);
        this.weatherCondition = weatherCondition;
    }

    private int kelvinToCelsius(int temp) {
        return temp - 273;
    }

    public Date getDate() {
        return date;
    }

    public int getMinTemp() {
        return temp_min;
    }

    public int getMaxTemp() {
        return temp_max;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public int day() {
        return date.getDay();
    }
}
