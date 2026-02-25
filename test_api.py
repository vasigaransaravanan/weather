import requests
import unittest

BASE_URL = "http://localhost:8097/weather"

class TestWeatherAPI(unittest.TestCase):

    def test_get_all(self):
        """Test GET /weather/all"""
        response = requests.get(f"{BASE_URL}/all")
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)
        print("Test /weather/all passed")

    def test_get_by_date(self):
        """Test GET /weather/date?date=..."""
        # Using a date that likely exists in the CSV data
        test_date = "19961101" # Formatter is yyyyMMdd-HH:mm but LocalDate.parse might expect ISO
        # Wait, CSVLoader uses DateTimeFormatter.ofPattern("yyyyMMdd-HH:mm") to parse CSV
        # But WeatherController uses LocalDate.parse(date) which expects yyyy-MM-dd
        
        test_date = "1996-11-01"
        response = requests.get(f"{BASE_URL}/date", params={"date": test_date})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)
        print(f"Test /weather/date?date={test_date} passed")

    def test_get_by_range(self):
        """Test GET /weather/range?start=...&end=..."""
        start_date = "1996-11-01"
        end_date = "1996-11-30"
        response = requests.get(f"{BASE_URL}/range", params={"start": start_date, "end": end_date})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)
        print(f"Test /weather/range?start={start_date}&end={end_date} passed")

    def test_get_by_condition(self):
        
        condition = "Haze"
        response = requests.get(f"{BASE_URL}/condition", params={"condition": condition})
        self.assertEqual(response.status_code, 200)
        self.assertIsInstance(response.json(), list)
        print(f"Test /weather/condition?condition={condition} passed")

if __name__ == "__main__":
    unittest.main()
