package com.weather.weather.controller;

import com.weather.weather.entity.Weather;
import com.weather.weather.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
        return repository.findByDate(LocalDate.parse(date));
    }

    @GetMapping("/range")
    public List<Weather> getByDateRange(@RequestParam String start,
            @RequestParam String end) {
        return repository.findByDateBetween(
                LocalDate.parse(start),
                LocalDate.parse(end));
    }

    @GetMapping("/condition")
    public List<Weather> getByCondition(@RequestParam String condition) {
        return repository.findByCondition(condition);
    }

    @GetMapping("/sorted")
    public List<Weather> getSortedByTemp() {
        return repository.findAll(
                Sort.by(Sort.Direction.DESC, "temperature"));
    }

    @GetMapping("/temp-greater")
    public List<Weather> getTempGreater(@RequestParam double value) {
        return repository.findByTemperatureGreaterThan(value);
    }

    @GetMapping("/temp-less")
    public List<Weather> getTempLess(@RequestParam double value) {
        return repository.findByTemperatureLessThan(value);
    }
}