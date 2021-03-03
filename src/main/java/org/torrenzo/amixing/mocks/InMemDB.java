package org.torrenzo.amixing.mocks;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class InMemDB implements ForecastProvider {

	private final Map<String, TreeMap<LocalDateTime, String>> forecastData = new HashMap<>();
	
	
	
	@PostConstruct
	void initDb() {
		TreeMap<LocalDateTime, String> forecastSlicesSeattle = createSlicesSeattle();
		TreeMap<LocalDateTime, String> forecastSlicesMemphis = createSlicesMemphis();
		TreeMap<LocalDateTime, String> forecastSlicesMumbai = createSlicesMumbai();
		TreeMap<LocalDateTime, String> forecastSlicesErin = createSlicesErin();
		
		forecastData.put("SEATTLE", forecastSlicesSeattle);
		forecastData.put("MEMPHIS", forecastSlicesMemphis);
		forecastData.put("MUMBAI", forecastSlicesMumbai);
		forecastData.put("ERIN", forecastSlicesErin);
	}



	private TreeMap<LocalDateTime, String> createSlicesSeattle() {
		LocalDateTime start = LocalDateTime.of(1967, 8, 12, 9, 0);
		List<LocalDateTime> times = IntStream.range(0, 10).boxed().map(i -> start.plusHours(2*i)).collect(Collectors.toList());
		List<String> forecasts = List.of("Foggy and cool", "Cool with Light rain", "partially cloudy with occasional drizzle", "occasional showers with sun breaks", "overcast with cool breeze", "some blue sky", "warm and clear", "cool and clear", "clear light breeze", "clear and cool");
		return createForecastSlices(times, forecasts);
	}



	private TreeMap<LocalDateTime, String> createSlicesMemphis() {
		LocalDateTime start = LocalDateTime.of(1967, 8, 12, 12, 0);
		List<LocalDateTime> times = IntStream.range(0, 10).boxed().map(i -> start.plusHours(2*i)).collect(Collectors.toList());
		List<String> forecasts = List.of("Foggy and cool", "Cool with Light rain", "partially cloudy with occasional drizzle", "cool and sunny with some clouds", "warm and sunny with light breeze", "sunny and warm", "sunny and hot", "warm and clear", "warm and clear with light breeze", "clear and cool");
		return createForecastSlices(times, forecasts);
	}



	private TreeMap<LocalDateTime, String> createSlicesMumbai() {
		LocalDateTime start = LocalDateTime.of(1967, 8, 12, 22, 30);
		List<LocalDateTime> times = IntStream.range(0, 10).boxed().map(i -> start.plusHours(2*i)).collect(Collectors.toList());
		List<String> forecasts = List.of("Foggy and cool", "Cool with Light rain", "partially cloudy with occasional drizzle", "sunny with some clouds", "warm and sunny", "sunny and warm", "sunny and hot", "warm and smog", "warm and clear", "clear and warm");
		return createForecastSlices(times, forecasts);
	}



	private TreeMap<LocalDateTime, String> createSlicesErin() {
		LocalDateTime start = LocalDateTime.of(1967, 8, 12, 15, 0);
		List<LocalDateTime> times = IntStream.range(0, 10).boxed().map(i -> start.plusHours(2*i)).collect(Collectors.toList());
		List<String> forecasts = List.of("Foggy and cool", "Cool with Light rain", "partially cloudy with occasional drizzle", "cool and sunny with some clouds", "partially cloudy with occasional drizzle", "partially cloudy with occasional drizzle", "partially cloudy with occasional drizzle", "warm and clear", "partially cloudy with occasional drizzle", "heavy rain");
		return createForecastSlices(times, forecasts);
	}

	private TreeMap<LocalDateTime, String> createForecastSlices(final List<LocalDateTime> slicetimes, final List<String> forecasts) {
		TreeMap<LocalDateTime, String> forecastSlices = IntStream.range(0, Math.min(forecasts.size(), slicetimes.size())).boxed().collect(Collectors.toMap(slicetimes::get, forecasts::get, (a, b) -> a.concat(b), TreeMap::new));
		return forecastSlices;
		
		
	}

	@Override
	public String forecastTimeLocation(LocalDateTime time, String location) {
		
		if (location == null) {
			return "insufficient data";
		}
		
		TreeMap<LocalDateTime, String> slices = forecastData.get(location.toUpperCase());
		
		if (slices != null && slices.isEmpty() == false) {
			LocalDateTime earliest = slices.firstKey().minusHours(2);
			LocalDateTime latest = slices.lastKey().plusHours(2);
			if (time.isBefore(earliest) || time.isAfter(latest)) {
				return "No forecast for that time";
			}
			return slices.floorEntry(time).getValue();
		
		}
		return "no forecasts for that location";
	}

	
	
	
}
