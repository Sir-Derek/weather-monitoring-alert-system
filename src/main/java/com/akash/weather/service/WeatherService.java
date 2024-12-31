/**
 * 
 */
package com.akash.weather.service;

import org.springframework.stereotype.Service;

import com.akash.weather.dto.WeatherDataDTO;
import com.akash.weather.model.WeatherData;

/**
 * @author Akash Bais
 *
 */

@Service
public interface WeatherService {

	WeatherDataDTO getMostRecentWeatherData(String city);

	WeatherData fetchWeatherForCity(String city);

    void saveWeatherData(WeatherData weatherData);

}