package com.weather.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class WeatherController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @RequestMapping("/weather")
    public List<WeatherRequest> weather() {
        String weatherUrl = "https://samples.openweathermap.org/data/2.5/forecast?q=London%2Cus&appid=d2929e9483efc82c82c32ee7%20e02d563e";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(weatherUrl, String.class);
        return processWeather(result);
    }

    private List<WeatherRequest> processWeather(String result) {
        int temp_min;
        int temp_max;

        Date date1 = null;

        String date;
        String weatherCondition;
        JSONObject root = new JSONObject(result);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray weatherObject = root.getJSONArray("list");

        List<WeatherRequest> requestArrayList = new ArrayList<>();

        Set dateSet = new HashSet<>();
        for (int i = 0; i < weatherObject.length(); i++) {
            JSONObject arrayElement = weatherObject.getJSONObject(i);
            JSONObject main = arrayElement.getJSONObject("main");

            temp_min = main.getInt("temp_min");
            temp_max = main.getInt("temp_max");

            weatherCondition = arrayElement.getJSONArray("weather").getJSONObject(0).getString("main");
            date = arrayElement.getString("dt_txt");
            String day = date.substring(0, 10);
            if (!dateSet.add(day)) {
                continue;
            }
            try {
                date1 = df.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            WeatherRequest weatherRequest = new WeatherRequest(date1, temp_min, temp_max, weatherCondition);

            requestArrayList.add(weatherRequest);

            if (dateSet.size() == 3) {
                break;
            }
        }

        return requestArrayList;
    }
}
