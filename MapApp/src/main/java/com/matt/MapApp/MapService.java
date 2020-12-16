package com.matt.MapApp;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

@Service
public class MapService {
	@Value("${api_key}")
	private String apiKey;
	
	public void addCoordinates(Location location) {
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + location.getCity() + "," + location.getState() + "&key=" + apiKey;
		RestTemplate restTemplate = new RestTemplate();
		GeocodingResponse response = restTemplate.getForObject(url, GeocodingResponse.class);
		Location coordinates = response.getResults().get(0).getGeometry().getLocation();
		location.setLat(coordinates.getLat());
		location.setLng(coordinates.getLng());
	}
	// random number between -180 and 180
	// random number between -360 and 360
	public Location randomizeCoords() {
		Location location = new Location();
		Random rand = new Random();
		double randomLongitude = (rand.nextDouble() * 360) - 180;
		double randomLatitude = (rand.nextDouble() * 180) - 90;
		location.setLng(String.valueOf(randomLongitude));
		location.setLat(String.valueOf(randomLatitude));
			
		return location;
		
	}
	
}
