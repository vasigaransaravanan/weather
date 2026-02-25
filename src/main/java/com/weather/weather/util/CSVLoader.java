package com.weather.weather.util;

import com.weather.weather.entity.Weather;
import com.weather.weather.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CSVLoader implements CommandLineRunner {

    private final WeatherRepository repository;

    public CSVLoader(WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        InputStream is = getClass().getResourceAsStream("/test.csv");

        if (is == null) {
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm");

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",");

            try {
                Weather w = new Weather();

                w.setDate(LocalDate.parse(data[0], formatter));
                w.setCondition(data[1]);
                w.setTemperature(data[11].isEmpty() ? 0 : Double.parseDouble(data[11]));

                w.setHumidity(data[6].isEmpty() ? 0 : Double.parseDouble(data[6]));
                w.setPressure(data[8].isEmpty() ? 0 : Double.parseDouble(data[8]));

                repository.save(w);

            } catch (Exception e) {
                // Skip invalid rows
            }
        }
    }
}