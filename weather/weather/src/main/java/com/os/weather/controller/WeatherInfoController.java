package com.os.weather.controller;

import com.os.weather.common.BaseController;
import com.os.weather.entity.Weather;
import com.os.weather.services.service.ListCitiesActionService;
import com.os.weather.utility.WeatherHelper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Controller
public class WeatherInfoController extends BaseController {

    @GetMapping("/weatherinfo")
    public String searchWeather() {
        return "weather/weatherinfo";
    }

    @GetMapping("/likedcities")
    public String showFellow() {
        return "weather/likedcities";
    }
}
