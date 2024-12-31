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
@AllArgsConstructor
@ToString
public class DailySummaryDTO {
	
	private String city;
    private double avgTemp;
    private double maxTemp;
    private double minTemp;
    private String dominantCondition;
    
}
