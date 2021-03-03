package org.torrenzo.amixing.services.impl;

import org.springframework.stereotype.Service;
import org.torrenzo.amixing.services.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Override
	public String forecast() {
		return "Check! Cold as fuck y'all!";
	}

}
