/**
 * 
 */
package com.akash.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akash.weather.model.UserPreferences;
import com.akash.weather.service.UserPreferenceImpl;
/**
 * @author Akash Bais
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/preferences")
public class UserPreferencesController {

    @Autowired
    private UserPreferenceImpl userPreferenceImpl;

    // Save user preferences
    @PostMapping("/save")
    public ResponseEntity<String> saveUserPreferences(@RequestBody UserPreferences userPreferences) {
    	System.out.println(userPreferences);
    	userPreferenceImpl.saveUserPreferences(userPreferences);
        return ResponseEntity.ok("User preferences saved successfully!");
    }
}