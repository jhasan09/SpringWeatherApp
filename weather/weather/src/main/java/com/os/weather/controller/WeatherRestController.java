package com.os.weather.controller;

import com.os.weather.entity.LikedCity;
import com.os.weather.entity.Weather;
import com.os.weather.repository.WeatherRepository;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
public class WeatherRestController {
    private WeatherRepository weatherRepository;

    public WeatherRestController(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @RequestMapping(value = "/savecity", method = RequestMethod.POST)
    public @ResponseBody HashMap saveCity(@RequestBody Weather weatherInfo, HttpServletRequest request) {
        LikedCity likedCity = weatherRepository.findByCityName(weatherInfo.location);
        if (likedCity == null) {
            likedCity = new LikedCity();
        }

        likedCity.setCityName(weatherInfo.location);
        likedCity.setWeather(weatherInfo.getWeather());
        likedCity.setTemperature(weatherInfo.getTemp());
        likedCity.setHumidity(weatherInfo.getHumidity());
        likedCity.setWindSpeed(weatherInfo.getWind());
        weatherRepository.save(likedCity);

        HashMap<String, String> map = new HashMap<>();
        map.put("responseText", weatherInfo.location + " added to liked list");
        return map;
    }

    @RequestMapping(value = "/deletecity", method = RequestMethod.POST)
    public @ResponseBody HashMap deleteCity(@RequestBody Weather weatherInfo, HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        LikedCity likedCity = weatherRepository.findByCityName(weatherInfo.location);
        if (likedCity == null) {
            map.put("responseText", weatherInfo.location + "  not found in liked list");
            return map;
        }

        weatherRepository.delete(likedCity);
        map.put("responseText", weatherInfo.location + " removed from liked list");
        return map;
    }
}
