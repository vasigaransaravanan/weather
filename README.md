# Weather API

Simple Spring Boot application for managing weather data.

## Features
- Import weather data from CSV on startup.
- Find weather records by date, range, or condition.
- Sort data by temperature.
- Filter by temperature, humidity, and pressure thresholds.

## Setup
1. Ensure MongoDB is running on `localhost:27017`.
2. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## API Endpoints
- `GET /weather/all`: Get all records.
- `GET /weather/date?date=YYYY-MM-DD`: Search by date.
- `GET /weather/range?start=YYYY-MM-DD&end=YYYY-MM-DD`: Search between dates.
- `GET /weather/condition?condition=Sunny`: Filter by condition.
- `GET /weather/sorted`: Sort by temperature descending.
