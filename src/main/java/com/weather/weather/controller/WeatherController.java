package com.weather.weather.controller;

import com.weather.weather.entity.Weather;
import com.weather.weather.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository repository;

    @GetMapping("/all")
    public List<Weather> getAll() {
        return repository.findAll();
    }

    @GetMapping("/date")
    public List<Weather> getByDate(@RequestParam String date) {
        return repository.findByDatetimeUtc(parseDateTime(date));
    }

    @GetMapping("/range")
    public List<Weather> getByDateRange(@RequestParam String start,
            @RequestParam String end) {
        return repository.findByDatetimeUtcBetween(
                parseDateTime(start),
                parseDateTime(end));
    }

    @GetMapping("/condition")
    public List<Weather> getByCondition(@RequestParam String condition) {
        return repository.findByConds(condition);
    }

    @GetMapping("/sorted")
    public List<Weather> getSortedByTemp() {
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, "tempm"));
    }

    @GetMapping("/temp-greater")
    public List<Weather> getTempGreater(@RequestParam double value) {
        return repository.findByTempmGreaterThan(value);
    }

    @GetMapping("/temp-less")
    public List<Weather> getTempLess(@RequestParam double value) {
        return repository.findByTempmLessThan(value);
    }

    @GetMapping("/wind-greater")
    public List<Weather> getWindGreater(@RequestParam double value) {
        return repository.findByWspdmGreaterThan(value);
    }

    @GetMapping("/wind-less")
    public List<Weather> getWindLess(@RequestParam double value) {
        return repository.findByWspdmLessThan(value);
    }

    @GetMapping("/vis-greater")
    public List<Weather> getVisGreater(@RequestParam double value) {
        return repository.findByVismGreaterThan(value);
    }

    @GetMapping("/vis-less")
    public List<Weather> getVisLess(@RequestParam double value) {
        return repository.findByVismLessThan(value);
    }

    @GetMapping("/humidity-greater")
    public List<Weather> getHumidityGreater(@RequestParam double value) {
        return repository.findByHumGreaterThan(value);
    }

    @GetMapping("/humidity-less")
    public List<Weather> getHumidityLess(@RequestParam double value) {
        return repository.findByHumLessThan(value);
    }

    @GetMapping("/pressure-greater")
    public List<Weather> getPressureGreater(@RequestParam double value) {
        return repository.findByPressuremGreaterThan(value);
    }

    @GetMapping("/pressure-less")
    public List<Weather> getPressureLess(@RequestParam double value) {
        return repository.findByPressuremLessThan(value);
    }

    private LocalDateTime parseDateTime(String dateStr) {
        try {
            return LocalDateTime.parse(dateStr);
        } catch (Exception e) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");
            return LocalDateTime.parse(dateStr, formatter);
        }
    }
}