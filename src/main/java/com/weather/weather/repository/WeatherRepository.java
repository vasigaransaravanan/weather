package com.weather.weather.repository;

import com.weather.weather.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

    List<Weather> findByDatetimeUtc(LocalDateTime date);

    List<Weather> findByDatetimeUtcBetween(LocalDateTime start, LocalDateTime end);

    List<Weather> findByConds(String condition);

    List<Weather> findByTempmGreaterThan(double value);

    List<Weather> findByTempmLessThan(double value);

    List<Weather> findByHumGreaterThan(double value);

    List<Weather> findByHumLessThan(double value);

    List<Weather> findByPressuremGreaterThan(double value);

    List<Weather> findByPressuremLessThan(double value);

    List<Weather> findByWspdmGreaterThan(double value);

    List<Weather> findByWspdmLessThan(double value);

    List<Weather> findByVismGreaterThan(double value);

    List<Weather> findByVismLessThan(double value);

}