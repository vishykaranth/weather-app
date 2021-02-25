package com.weather.demo;

import java.util.Date;

public class WeatherStatusResponse {
    private Date date;
    private  int temp_min;
    private  int temp_max;
    private  String weatherCondition;
    private boolean hot;
    private String message;
    private boolean isRain;

    public WeatherStatusResponse(Date date, int temp_min, int temp_max, String weatherCondition) {

        this.date = date;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.weatherCondition = weatherCondition;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public int getMaxTemp() {
        return temp_max;
    }

    public int getMinTemp() {
        return temp_min;
    }

    public Date getDate() {
        return date;
    }

    public void setMaxTemp(int temp_max) {
        if (temp_max > this.temp_max){
            this.temp_max = temp_max;
        }

        if(temp_max > 40 && message == null){
            hot = true;
        }
    }

    public void setMinTemp(int minTemp) {
        if (minTemp < this.temp_min){
            this.temp_min = minTemp;
        }
    }

    public void setWeatherCondition(String weatherCondition) {
        if("Rain".equalsIgnoreCase(weatherCondition)){
            isRain = true;
            message = "Carry Umbrella";
        }

        this.weatherCondition = weatherCondition;
    }

    public String getMessage(){
        if(isRain){
            message = "Carry umbrella";
        }else if(hot){
            message = "Use sunscreen lotion";
        }

        return message;
    }
}
