package com.weather.weather.util;

import com.weather.weather.entity.Weather;
import com.weather.weather.repository.WeatherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
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

        // Skip header row
        br.readLine();

        while ((line = br.readLine()) != null) {

            String[] data = line.split(",", -1);

            try {
                Weather w = new Weather();

                w.setDatetimeUtc(LocalDateTime.parse(data[0], formatter));
                w.setConds(data[1]);
                w.setDewptm(safeParseDouble(data[2]));
                w.setFog(safeParseInt(data[3]));
                w.setHail(safeParseInt(data[4]));
                w.setHeatindexm(safeParseDouble(data[5]));
                w.setHum(safeParseDouble(data[6]));
                w.setPrecipm(safeParseDouble(data[7]));
                w.setPressurem(safeParseDouble(data[8]));
                w.setRain(safeParseInt(data[9]));
                w.setSnow(safeParseInt(data[10]));
                w.setTempm(safeParseDouble(data[11]));
                w.setThunder(safeParseInt(data[12]));
                w.setTornado(safeParseInt(data[13]));
                w.setVism(safeParseDouble(data[14]));
                w.setWdird(safeParseInt(data[15]));
                w.setWdire(data[16]);
                w.setWgustm(safeParseDouble(data[17]));
                w.setWindchillm(safeParseDouble(data[18]));
                w.setWspdm(safeParseDouble(data[19]));

                repository.save(w);

            } catch (Exception e) {
                // Skip invalid rows
            }
        }
    }

    private Double safeParseDouble(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer safeParseInt(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}