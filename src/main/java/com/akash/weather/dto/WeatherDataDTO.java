/**
 * 
 */
package com.akash.weather.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Akash Bais
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeatherDataDTO {

	private String mainCondition;
	private double temp;
	private double feelsLike;
	private LocalDate date;

}
