/**
 * 
 */
package com.akash.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Akash Bais
 *
 */

@AllArgsConstructor
@Getter
@Setter
public class UserPreferencesDTO {

	private String city;
    private String email;
    private String unit; // e.g., Celsius or Fahrenheit
    private Double minTemperature;
    private Double maxTemperature;
    
}
