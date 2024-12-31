/**
 * 
 */
package com.akash.weather.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.akash.weather.dto.DailySummaryDTO;
import com.akash.weather.dto.WeatherDataDTO;
import com.akash.weather.model.WeatherData;

/**
 * @author Akash Bais
 *
 */

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {

	@Query("SELECT new com.akash.weather.dto.DailySummaryDTO(" +
		       "wd.city, " +
		       "AVG(wd.temp), " +
		       "MAX(wd.temp), " +
		       "MIN(wd.temp), " +
		       "(SELECT wd2.mainCondition FROM WeatherData wd2 " +
		       "WHERE wd2.city = wd.city " +
		       "AND wd2.date = :date " +
		       "GROUP BY wd2.mainCondition " +
		       "ORDER BY COUNT(wd2.mainCondition) DESC " +
		       "LIMIT 1) " +
		       ") " +
		       "FROM WeatherData wd " +
		       "WHERE wd.city = :city " +
		       "AND wd.date = :date " +
		       "GROUP BY wd.city")
		DailySummaryDTO findWeatherSummaryByCityAndDate(@Param("city") String city, @Param("date") LocalDate date);


	@Query("SELECT new com.akash.weather.dto.WeatherDataDTO(wd.mainCondition, wd.temp, wd.feelsLike, wd.date) " +
	       "FROM WeatherData wd " +
	       "WHERE wd.city = :city " +
	       "ORDER BY wd.date DESC" )
	List<WeatherDataDTO> findMostRecentByCity(@Param("city")String city);

//	 WeatherData findFirstByCityOrderByDateDesc(@Param("city") String city);

	@Query("SELECT wd.city, wd.temp " + "FROM WeatherData wd " + "WHERE wd.city IN :cities "
			+ "AND wd.date = (SELECT MAX(wd2.date) FROM WeatherData wd2 WHERE wd2.city = wd.city)")
	List<Object[]> findCurrentTemperatures(@Param("cities") Set<String> cities);

	/**
	 * @param yesterday
	 */
	void deleteByDate(LocalDate yesterday);

}
