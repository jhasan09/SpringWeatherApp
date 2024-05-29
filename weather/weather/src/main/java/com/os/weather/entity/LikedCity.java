package com.os.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "liked_city")
public class LikedCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private int id;

    @Column(name = "city_name", unique = true)
    private String cityName;

    @Column(name = "weather")
    private String weather;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "humidity")
    private double humidity;

    @Column(name = "wind_speed")
    private double windSpeed;

}

