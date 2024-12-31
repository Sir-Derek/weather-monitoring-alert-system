/**
 * 
 */
package com.akash.weather.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_preferences")
@Getter
@Setter
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String unit; // e.g., Celsius or Fahrenheit

    private Double minTemperature;
    
    private Double maxTemperature;

    private boolean alertOn; // Indicates whether alerts are on or off
}
