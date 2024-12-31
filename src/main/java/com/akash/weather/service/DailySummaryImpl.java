/**
 * 
 */
package com.akash.weather.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.weather.dto.DailySummaryDTO;
import com.akash.weather.model.DailySummary;
import com.akash.weather.repository.DailySummaryRepository;
import com.akash.weather.repository.WeatherRepository;

/**
 * @author Akash Bais
 *
 */

@Service
public class DailySummaryImpl implements DailySummaryService{

	@Autowired
	private WeatherRepository weatherRepository; 
	
	@Autowired
	private DailySummaryRepository dailyWeatherSummary; 

	public DailySummaryDTO getDailySummary(String city, LocalDate date) {
		return weatherRepository.findWeatherSummaryByCityAndDate(city, date);
	}

	public void saveDailySummary(DailySummaryDTO dailySummarydto,LocalDate date) {

//		Long id;
//	    String city;
//		double avgTemp;
//		double maxTemp;
//		double minTemp;
//		String dominantCondition;
//		LocalDate date;
		
		DailySummary dailySummary = new DailySummary();
		dailySummary.setCity(dailySummarydto.getCity());
		dailySummary.setAvgTemp(dailySummarydto.getAvgTemp());
		dailySummary.setMaxTemp(dailySummarydto.getMaxTemp());
		dailySummary.setMinTemp(dailySummarydto.getMinTemp());
		dailySummary.setDominantCondition(dailySummarydto.getDominantCondition());
		dailySummary.setDate(date);
		
		dailyWeatherSummary.save(dailySummary);
		
	}

	/**
	 * 
	 */
	public void deleteAllSummaries() {
		dailyWeatherSummary.deleteAll();
	}
}
