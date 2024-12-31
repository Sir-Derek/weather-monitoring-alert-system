/**
 * 
 */
package com.akash.weather.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Akash Bais
 *
 */
@Getter
@Setter
@Entity
public class WeatherData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String city;
	private double temp;
	private double feelsLike;
	private String mainCondition;
	private long dt;
	private LocalDate date;

}
