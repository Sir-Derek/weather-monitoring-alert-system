/**
 * 
 */
package com.akash.weather.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akash.weather.model.UserPreferences;
import com.akash.weather.repository.PreferencesRepository;
import com.akash.weather.repository.WeatherRepository;

/**
 * @author Akash Bais
 *
 */

@Service
public class UserPreferenceImpl {

	

    @Autowired
    private PreferencesRepository preferencesRepository;
    
    @Autowired
    private WeatherRepository weatherRepository;
    
    
    public UserPreferences saveUserPreferences(UserPreferences userPreferences) {
    	System.out.println(userPreferences.toString());
        return preferencesRepository.save(userPreferences);
    }
    
    //converting list of object into map
    public Map<String, Double> fetchCurrentTemperatures(Set<String> cities) {
        List<Object[]> results = weatherRepository.findCurrentTemperatures(cities);
        Map<String, Double> temperatureMap = new HashMap<>();
        
        for (Object[] result : results) {
            String city = (String) result[0];
            Double temperature = (Double) result[1];
            temperatureMap.put(city, temperature);
        }
        
        return temperatureMap;
    }
}
