/**
 * 
 */
package com.akash.weather.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Akash Bais
 *
 */
@Getter
@Setter
public class WeatherSettings {

	 private String city;
	    private String unit;
	    private Integer minTemp;
	    private Integer maxTemp;
	    private boolean enableAlerts;
	    
}
