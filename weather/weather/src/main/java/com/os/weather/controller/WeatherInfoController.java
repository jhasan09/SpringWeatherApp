package com.os.weather.controller;

import com.os.weather.entity.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class WeatherInfoController {
    private final String apiKey = "bab221b4a0c72d3bb1c0c48d966621e8";
    private String error;
    private boolean isValid;

    @GetMapping("/weatherinfo")
    public String searchWeather() {
        return "weather/weatherinfo";
    }

    @GetMapping("/api/search")
    @ResponseBody
    public ResponseEntity<Weather> createStudent(@RequestParam String city) {
        Weather weatherInfo = new Weather();
        String request = getResponse(
                "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + apiKey);
        if (!(request.startsWith("Error"))) {
            JSONObject ob = getDataObject(request);
            System.out.println(isValid);
            if (isValid) {
                JSONObject main = (JSONObject) ob.get("main");
                JSONObject coord = (JSONObject) ob.get("coord");
                JSONArray weatherArray = (JSONArray) ob.get("weather");
                JSONObject weatherObj = (JSONObject) weatherArray.get(0);

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
            } else {
                weatherInfo.setError("error occured");
            }
        } else {
            weatherInfo.setError("error occured");
        }

        ResponseEntity<Weather> weatherResponseEntity = new ResponseEntity<>(weatherInfo, HttpStatus.CREATED);
        return weatherResponseEntity;
    }

    private JSONObject getDataObject(String response) {
        JSONObject ob = null;
        try {
            ob = (JSONObject) new JSONParser().parse(response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            if (ob.get("cod").toString().equals("200")) {
                isValid = true;
            } else {
                System.out.println("COD is:" + ob.get("cod").toString());
                error = ob.get("message").toString();
            }
        } catch (Exception e) {
            error = "Error in Fetching the Request";
        }
        return ob;
    }

    private String getResponse(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            return content.toString();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return "Error: " + e.toString();
        }
    }

}
