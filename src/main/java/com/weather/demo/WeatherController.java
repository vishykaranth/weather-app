package com.weather.demo;

import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

@RestController
public class WeatherController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/weather")
    public List<WeatherStatusResponse> weather() {
        String weatherUrl = "https://samples.openweathermap.org/data/2.5/forecast?q=London%2Cus&appid=d2929e9483efc82c82c32ee7%20e02d563e";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(weatherUrl, String.class);
        return processWeather(result);
    }

    private List<WeatherStatusResponse> processWeather(String result)  {
        int temp_min;
        int temp_max;

        Date date1 = null;

        String date;
        String weatherCondition;
        JSONObject root = new JSONObject(result);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray weatherObject = root.getJSONArray("list");

        List<WeatherRequest> requestArrayList = new ArrayList<>();

        for (int i=0;i<weatherObject.length();i++){
            JSONObject arrayElement = weatherObject.getJSONObject(i);
            JSONObject main = arrayElement.getJSONObject("main");

            temp_min = main.getInt("temp_min");
            temp_max = main.getInt("temp_max");

            weatherCondition = arrayElement.getJSONArray("weather").getJSONObject(0).getString("main");
            date = arrayElement.getString("dt_txt");

            try {
                date1 = df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            WeatherRequest weatherRequest = new WeatherRequest(date1, temp_min, temp_max, weatherCondition);

            requestArrayList.add(weatherRequest);
        }

        List<WeatherStatusResponse> weatherStatusResponseList = new ArrayList<>();
        int day = -1 ;
        WeatherStatusResponse weatherStatusResponse = null;
        for (WeatherRequest request : requestArrayList) {

            if (day == -1 || day != request.day()){
                day = request.day();
                weatherStatusResponse = new WeatherStatusResponse(request.getDate(), request.getMinTemp(), request.getMaxTemp(), request.getWeatherCondition());
                weatherStatusResponseList.add(weatherStatusResponse);
            }

            weatherStatusResponse.setMaxTemp(request.getMaxTemp());
            weatherStatusResponse.setMinTemp(request.getMinTemp());
            weatherStatusResponse.setWeatherCondition(request.getWeatherCondition());
        }

        return weatherStatusResponseList.subList(1, 4);
    }
}
