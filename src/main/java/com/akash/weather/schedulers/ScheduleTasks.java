/**
 * 
 */
package com.akash.weather.schedulers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.akash.weather.dto.DailySummaryDTO;
import com.akash.weather.model.WeatherData;
import com.akash.weather.repository.WeatherRepository;
import com.akash.weather.service.DailySummaryImpl;
import com.akash.weather.service.WeatherService;

/**
 * @author Akash Bais
 *
 */

@Component
@EnableScheduling
public class ScheduleTasks {

	@Autowired
	private WeatherService weatherService;

	@Autowired
	private DailySummaryImpl dailySummaryImpl;

	@Autowired
	private WeatherRepository weatherRepository;

	private static final List<String> cities = Arrays.asList("Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata",
			"Hyderabad");

	// Schedule to fetch weather data every 5 minutes (300,000 milliseconds)
	@Scheduled(fixedRate = 300000) // 5 minutes
	public void fetchWeatherDataPeriodically() {
		for (String city : cities) {
			WeatherData weatherData = weatherService.fetchWeatherForCity(city);
			weatherService.saveWeatherData(weatherData);
		}
	}

	// Scheduled to run every 24 hours: generates daily summaries and then deletes data
	// Runs at midnight every day // 24 hours
	@Scheduled(cron = "0 0 0 * * ?") 
	public void generateSummariesAndDeleteData() {
		// Step 1: Generate daily summaries
		generateDailySummaries();

		// Step 2: Delete yesterday's data
		deleteAllData();
	}
	
	// Scheduled to delete all daily summaries every 20 days
    @Scheduled(fixedRate = 1728000000) // 20 days in milliseconds
    public void deleteAllSummaries() {
        dailySummaryImpl.deleteAllSummaries();
        System.out.println("Deleted all daily summaries from the database.");
    }

	private void generateDailySummaries() {
		// Get yesterday's date for summaries
		LocalDate yesterday = LocalDate.now().minusDays(1);
		for (String city : cities) {
			DailySummaryDTO dailySummary = dailySummaryImpl.getDailySummary(city, yesterday);
			if (dailySummary != null) {
				dailySummaryImpl.saveDailySummary(dailySummary, yesterday);
				System.out.println("Summary for " + city + " on " + yesterday + ": " + dailySummary);
			}
		}
		System.out.println("Daily summaries generated for " + yesterday);
	}

	private void deleteAllData() {
		weatherRepository.deleteAll();
		System.out.println("Deleted all data from WeatherData table.");
	}
	
}
