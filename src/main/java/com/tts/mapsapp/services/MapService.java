package com.tts.mapsapp.services;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tts.mapsapp.deserialization.GeocodingResponse;
import com.tts.mapsapp.deserialization.GeocodingReverseResponse;
import com.tts.mapsapp.deserialization.Location;
import com.tts.mapsapp.deserialization.RandomCoords;

@Service
public class MapService {
    @Value("${api_key}")
    private String apiKey;
  
    public void addCoordinates(Location location) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
        location.getCity() + "," + location.getState() + "&key=" + apiKey;
    
        RestTemplate restTemplate = new RestTemplate();
        //grabs the response from the url and grabs the objects
        GeocodingResponse response = 
        		restTemplate.getForObject(url, GeocodingResponse.class);
        
        Location coordinates = response.getResults().get(0).getGeometry().getLocation();
    //^^get first object, get geometry from within first object, inside that getting coordinates^^
        location.setLat(coordinates.getLat());
        location.setLng(coordinates.getLng());
    }

    public void addPlace(Location location)
    {
    	String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
    	        location.getCity() + "," + location.getState() + "&key=" + apiKey;
    	
    	RestTemplate restTemplate = new RestTemplate();
    	
    	GeocodingReverseResponse response = restTemplate.getForObject(url, GeocodingReverseResponse.class);
    	if(response.getResults().size()== 0)
    	{
    		location.setCity("Unknown");
    	}
    	else
    	{
    		String formattedAddress = response.getResults().get(0).getFormatted_address();
    		location.setCity(formattedAddress);
    	}
    }
    
//    public void randomCoordinates(Location location)
//    {
//    	String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + 
//    	        location.getCity() + "," + location.getState() + "&key=" + apiKey;
//    	    
//    	        RestTemplate restTemplate = new RestTemplate();
//    	        //grabs the response from the url and grabs the objects
//    	        RandomCoords rCoordinates = 
//    	        		restTemplate.getForObject(generateRandomCoords());
//    	        
//    	        Location coordinates = location1.getLocation();
//    	    //^^get first object, get geometry from within first object, inside that getting coordinates^^
//    	        location1.setLat(coordinates.getLat());
//    	        location1.setLng(coordinates.getLng());
//    }
}