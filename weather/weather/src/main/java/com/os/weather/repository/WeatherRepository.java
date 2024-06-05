package com.os.weather.repository;

import com.os.weather.entity.LikedCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("weatherRepository")
public interface WeatherRepository extends JpaRepository<LikedCity, Long> {
    LikedCity findByCityNameAndUserName(String cityName, String userName);

}