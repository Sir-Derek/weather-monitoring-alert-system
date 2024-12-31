/**
 * 
 */
package com.akash.weather.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.akash.weather.dto.WeatherDataDTO;
import com.akash.weather.model.WeatherData;
import com.akash.weather.repository.WeatherRepository;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Akash Bais
 *
 */
@Service
public class WeatherServiceImpl implements WeatherService {

	@Value("${openweathermap.api.key}")
	private String apiKey;
	
	@Autowired
	private WeatherRepository weatherRepository;
	

//	@Override
//	public WeatherDataDTO getWeatherByCity(String city) {
//		// Retrieve the most recent weather data for the specified city
//		WeatherDataDTO firstByCityOrderByDateDesc = weatherRepository.findMostRecentByCity(city);
//		System.out.println(firstByCityOrderByDateDesc.toString());
//		
//		return weatherRepository.findMostRecentByCity(city);
//				
//	}

	public WeatherDataDTO getMostRecentWeatherData(String city) {
	    List<WeatherDataDTO> results = weatherRepository.findMostRecentByCity(city);
	    return results.isEmpty() ? null : results.get(0); // Return the first result or null if none
	}


	// Fetch weather data for a specific city
	public WeatherData fetchWeatherForCity(String city) {
		String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s", city, apiKey);
		RestTemplate restTemplate = new RestTemplate();

		JsonNode response = restTemplate.getForObject(url, JsonNode.class);
//		System.out.print(response.toPrettyString());

		double temperature = response.path("main").path("temp").asDouble();
		double feelsLike = response.path("main").path("feels_like").asDouble();
		String mainCondition = response.path("weather").get(0).path("main").asText();

		long timestamp = response.path("dt").asLong();
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();

		WeatherData weatherData = new WeatherData();
		weatherData.setCity(city);
		weatherData.setTemp(convertKelvinToCelsius(temperature));
		weatherData.setFeelsLike(convertKelvinToCelsius(feelsLike));
		weatherData.setMainCondition(mainCondition);
		weatherData.setDt(timestamp);
		weatherData.setDate(date);

		return weatherData;
	}

	public void saveWeatherData(WeatherData weatherData) {
		weatherRepository.save(weatherData);
	}

	private double convertKelvinToCelsius(double kelvin) {
		return kelvin - 273.15;
	}
}
