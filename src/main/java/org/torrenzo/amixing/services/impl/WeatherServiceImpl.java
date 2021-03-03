package org.torrenzo.amixing.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.torrenzo.amixing.mocks.ForecastProvider;
import org.torrenzo.amixing.services.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final ForecastProvider forecastProvider;
	
	private static Integer timeindex = 0;

	@Autowired
	public WeatherServiceImpl(ForecastProvider forecastProvider) {
		this.forecastProvider = forecastProvider;
	}
	
	private LocalDateTime seattlebase = LocalDateTime.of(1967, 8, 12, 9, 0);
	
	@Override
	public String forecast() {
		return forecastProvider.forecastTimeLocation(seattlebase.plusHours(3).plusHours(2 * timeindex++), "memphis");
	}

}
