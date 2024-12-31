/**
 * 
 */
package com.akash.weather.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akash.weather.dto.UserPreferencesDTO;
import com.akash.weather.model.UserPreferences;

/**
 * @author Akash Bais
 *
 */
public interface PreferencesRepository extends JpaRepository<UserPreferences,Long>{

	@Query("SELECT new com.akash.weather.dto.UserPreferencesDTO(u.city, u.email, u.unit, u.minTemperature, u.maxTemperature) " +
		       "FROM UserPreferences u WHERE u.alertOn = :alertOn")
		List<UserPreferencesDTO> findUserPreferencesDTOByAlertOn(@Param("alertOn") boolean alertOn);


}
