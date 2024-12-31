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
public interface DailySummaryService {

	DailySummaryDTO getDailySummary(String city, LocalDate date);

	void saveDailySummary(DailySummaryDTO dailySummarydto, LocalDate date);
}
