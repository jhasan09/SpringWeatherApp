package com.os.weather.controller;

import com.os.weather.common.BaseController;
import com.os.weather.entity.LikedCity;
import com.os.weather.entity.Weather;
import com.os.weather.repository.WeatherRepository;
import com.os.weather.services.service.ListCitiesActionService;
import com.os.weather.utility.WeatherHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WeatherRestController extends BaseController {
    private final String apiKey = "bab221b4a0c72d3bb1c0c48d966621e8";
    private WeatherRepository weatherRepository;
    private ListCitiesActionService listCitiesActionService;

    public WeatherRestController(WeatherRepository weatherRepository, ListCitiesActionService listCitiesActionService) {
        this.weatherRepository = weatherRepository;
        this.listCitiesActionService = listCitiesActionService;
    }

    @GetMapping("/api/search")
    @ResponseBody
    public ResponseEntity<Weather> serachWeather(@RequestParam String city) {
        Weather weatherInfo = new Weather();
        String request = WeatherHelper.getResponse(
                "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);
        if (!(request.startsWith("Error"))) {
            JSONObject ob = WeatherHelper.getDataObject(request);
            if (ob != null) {
                JSONObject main = (JSONObject) ob.get("main");
                JSONObject coord = (JSONObject) ob.get("coord");
                JSONArray weatherArray = (JSONArray) ob.get("weather");
                JSONObject weatherObj = (JSONObject) weatherArray.get(0);
                JSONObject windObj = (JSONObject) ob.get("wind");

                weatherInfo.setTemp(Double.parseDouble(main.get("temp").toString()));
                weatherInfo.setFeels_like(Double.parseDouble(main.get("feels_like").toString()));
                weatherInfo.setTemp_min(Double.parseDouble(main.get("temp_min").toString()));
                weatherInfo.setTemp_max(Double.parseDouble(main.get("temp_max").toString()));
                weatherInfo.setPressure(Double.parseDouble(main.get("pressure").toString()));
                weatherInfo.setHumidity(Double.parseDouble(main.get("humidity").toString()));
                weatherInfo.setLocation(ob.get("name").toString());
                weatherInfo.setLongitude(Double.parseDouble(coord.get("lon").toString()));
                weatherInfo.setLatitude(Double.parseDouble(coord.get("lat").toString()));
                weatherInfo.setWeather(weatherObj.get("main").toString());
                weatherInfo.setWind(Double.parseDouble(windObj.get("speed").toString()));
            } else {
                weatherInfo.setError("Error Occured!");
            }
        } else {
            weatherInfo.setError("Error Occured!");
        }

        ResponseEntity<Weather> weatherResponseEntity = new ResponseEntity<>(weatherInfo, HttpStatus.CREATED);
        return weatherResponseEntity;
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

    @RequestMapping(value = "/api/likedcitylist", method = RequestMethod.GET)
    @ResponseBody
    public String listLikedCities(@RequestParam Map<String, Object> parameters) {
        return renderOutput(listCitiesActionService, parameters);
    }

    @RequestMapping(value = "/deletecity", method = RequestMethod.POST)
    public @ResponseBody LikedCity deleteCity(@RequestBody Weather weatherInfo, HttpServletRequest request) {
        LikedCity likedCity = weatherRepository.findByCityName(weatherInfo.location);
        if (likedCity == null) {
            return null;
        }

        weatherRepository.delete(likedCity);
        return likedCity;
    }
}
