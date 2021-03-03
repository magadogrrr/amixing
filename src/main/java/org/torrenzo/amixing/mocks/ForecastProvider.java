package org.torrenzo.amixing.mocks;

import java.time.LocalDateTime;

public interface ForecastProvider {
	
	String forecastTimeLocation(LocalDateTime time, String location);
}
