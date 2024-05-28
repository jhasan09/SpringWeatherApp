package com.os.weather.entity;

public class Weather {
    public double temp;
    public double feels_like;
    public double temp_min;
    public double temp_max;
    public double pressure;
    public double humidity;
    public String location;
    public double longitude;
    public double latitude;
    public String weather;
    public String error;

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
}
