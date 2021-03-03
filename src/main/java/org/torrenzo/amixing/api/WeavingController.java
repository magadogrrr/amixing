package org.torrenzo.amixing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.torrenzo.amixing.services.WeatherService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("main-scene.fxml")
public class WeavingController {

	private final WeatherService weatherService;
	
	@Autowired
	public WeavingController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@FXML
	private Label forecastDisplay;
	
	public void loadForecast(ActionEvent e) {
		this.forecastDisplay.setText(weatherService.forecast());
	}
}
