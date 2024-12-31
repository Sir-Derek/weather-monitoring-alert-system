/**
 * 
 */
package com.akash.weather.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.weather.dto.DailySummaryDTO;
import com.akash.weather.dto.WeatherDataDTO;
import com.akash.weather.service.DailySummaryImpl;
import com.akash.weather.service.WeatherServiceImpl;

/**
 * @author Akash Bais
 *
 */

@RestController
@RequestMapping("/api/weather") // Base URL for all weather-related APIs
public class WeatherController {
	
	@Autowired
	private WeatherServiceImpl weatherServiceImpl; // Assume you have a service class
	
	@Autowired
	private DailySummaryImpl dailySummaryImpl;

	@GetMapping("/current/{city}")
	public ResponseEntity<WeatherDataDTO> getWeatherByCity(@PathVariable String city) {
		WeatherDataDTO weatherData = weatherServiceImpl.getMostRecentWeatherData(city); // Call the existing method
		if (weatherData != null) {
			return new ResponseEntity<>(weatherData, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // City not found
		}
	}
	// Endpoint to get daily weather summary
	@GetMapping("/summary/{city}/{date}")
	public ResponseEntity<DailySummaryDTO> getDailySummary(@PathVariable String city, @PathVariable String date) {
		LocalDate localDate = LocalDate.parse(date); // Convert String date to LocalDate
		System.out.println(localDate);
		DailySummaryDTO dailySummary = dailySummaryImpl.getDailySummary(city, localDate); // Call the existing method
		if (dailySummary != null) {
			return new ResponseEntity<>(dailySummary, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Summary not found
		}
	}
	
}
