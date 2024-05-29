package com.os.weather.entity;

public class Weather {
    private double temp;
    private double feels_like;
    private double temp_min;

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setFeels_like(double feels_like) {
        this.feels_like = feels_like;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setError(String error) {
        this.error = error;
    }

    private double temp_max;
    private double pressure;
    private double humidity;
    public String location;
    private double longitude;
    private double latitude;
    private double wind;

    public void setWind(double wind) {
        this.wind = wind;
    }

    private String weather;
    private String error;

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getLocation() {
        return location;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getWind() {
        return wind;
    }

    public String getWeather() {
        return weather;
    }

    public String getError() {
        return error;
    }
}
