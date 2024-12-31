/**
 * 
 */
package com.akash.weather.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.akash.weather.model.DailySummary;

/**
 * @author Akash Bais
 *
 */
public interface DailySummaryRepository extends JpaRepository < DailySummary ,Long> {

	@Query("SELECT ds FROM DailySummary ds " +
	           "WHERE ds.city = :city AND ds.date = :date") // Date must match yesterday
	    Optional<DailySummary> findSummaryByCityAndDate(@Param("city") String city, @Param("date") LocalDate date);
	
}
