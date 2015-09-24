package com.vsplc.weather.web_service;

import retrofit.RestAdapter;

/**
 * @author Swapnil Sonar
 */
public class RestClient {
	
	private static final String BASE_URL = "http://api.openweathermap.org/data/2.5";
	
	private RestInterface restInterface;

	public RestClient() {

		RestAdapter restAdapter = new RestAdapter.Builder()
				.setLogLevel(RestAdapter.LogLevel.FULL).setEndpoint(BASE_URL)
				.build();
		restInterface = restAdapter.create(RestInterface.class);
	}

	public RestInterface getRestInterface() {
		return restInterface;
	}
}
