package com.weather.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WeatherController {

    @RequestMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @RequestMapping("/weather")
    public String weather(){
        String weatherUrl = "https://samples.openweathermap.org/data/2.5/forecast?q=London%2Cus&appid=d2929e9483efc82c82c32ee7%20e02d563e";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(weatherUrl, String.class);
        return result;
    }
}
