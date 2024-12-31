/**
 * 
 */
package com.akash.weather.schedulers;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.akash.weather.dto.UserPreferencesDTO;
import com.akash.weather.model.UserPreferences;
import com.akash.weather.repository.PreferencesRepository;
import com.akash.weather.repository.WeatherRepository;
import com.akash.weather.service.EmailService;
import com.akash.weather.service.UserPreferenceImpl;

/**
 * @author Akash Bais
 *
 */

@Component
@EnableScheduling
public class Thresholdscheduler {

	@Autowired
	private PreferencesRepository preferencesRepository;

	@Autowired
	private WeatherRepository weatherRepository;

	@Autowired
	private UserPreferenceImpl userPreferenceImpl;

	@Autowired
	private EmailService emailService;

	@Scheduled(fixedRate = 20000) // Every 15 minutes
	public void checkTemperatureAlerts() throws Exception {
		
		System.out.print("-------------------- Email Service ---------------");

		List<UserPreferencesDTO> userPreferencesList = preferencesRepository.findUserPreferencesDTOByAlertOn(true);
		
		for (UserPreferencesDTO preferences : userPreferencesList) {
		    System.out.println("User: " + preferences.getEmail() + 
		                       ", City: " + preferences.getCity() + 
		                       ", Min Temp: " + preferences.getMinTemperature() + 
		                       ", Max Temp: " + preferences.getMaxTemperature());
		}
		

		Set<String> cities = userPreferencesList.stream().map(UserPreferencesDTO::getCity).collect(Collectors.toSet());

		Map<String, Double> currentTemperatures = userPreferenceImpl.fetchCurrentTemperatures(cities);
		
		System.out.println("Fetched temperatures: " + currentTemperatures);

		// Check each user's preferences against current temperatures
		for (UserPreferencesDTO preferences : userPreferencesList) {
			String city = preferences.getCity();
			Double currentTemperature = currentTemperatures.get(city);
			
			System.out.println("min temp preferences "+preferences.getMinTemperature());
			System.out.println("max temp preferences "+preferences.getMaxTemperature());
			if (currentTemperature != null) {
				// Check if the current temperature is outside the user's defined range
				if (currentTemperature < preferences.getMinTemperature()
						
						|| currentTemperature > preferences.getMaxTemperature()) {
					// Send alert
					System.out.println("Inside email block");
					emailService.sendEmailAlert(preferences.getEmail(), currentTemperature);
				}
			}
		}
	}

}
