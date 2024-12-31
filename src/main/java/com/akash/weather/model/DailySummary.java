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

@Entity
@Getter
@Setter
public class DailySummary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	private double avgTemp;
	private double maxTemp;
	private double minTemp;
	private String dominantCondition;
	private LocalDate date;

}
