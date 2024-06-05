package com.os.weather.entity;
import javax.persistence.*;

@Entity
@Table(name = "liked_city")
public class LikedCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    public void setId(int id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "weather")
    private String weather;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "wind_speed")
    private double windSpeed;

    @Column(name = "user_name", nullable = false)
    private String userName;

}

