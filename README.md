# WeatherInformationManager
1. This application gives the past weather conditions of an area with a particular pincode.
2. User calls:

GET http://localhost:8080/api/weather?pincode=411014&for_date=2020-10-15

Above REST api contains the pincode and date for which the weather information is required.

3. The backend extracts the pincode=411014.
4. It calls the GEOCODING API:
http://api.openweathermap.org/geo/1.0/zip?zip=411014,IN&appid=abcd1234efgh5678ijkl

Response contains latitude and Longtiude value of that location.

5. Then it calls the WEATHER API:

https://api.openweathermap.org/data/2.5/weather?lat=18.56&lon=73.91&appid=abcd1234efgh5678ijkl&units=metric

Response contains temperature, humidity, description, etc.

6. Data is saved into MYSQL , and returned to the client.
7. If the requested pincode and date are same as already saved in database then it fetches the value from weather_db table.
8. Pincode and corresponding Latitude and Longitude are saved in a separate table named as Location.


