package com.weather.weather.repository;

import com.weather.weather.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    List<Weather> findByDate(LocalDate date);

    List<Weather> findByDateBetween(LocalDate start, LocalDate end);

    List<Weather> findByCondition(String condition);

    List<Weather> findByTemperatureGreaterThan(double value);

    List<Weather> findByTemperatureLessThan(double value);

    List<Weather> findByHumidityGreaterThan(double value);

    List<Weather> findByHumidityLessThan(double value);

    List<Weather> findByPressureGreaterThan(double value);

    List<Weather> findByPressureLessThan(double value);

}