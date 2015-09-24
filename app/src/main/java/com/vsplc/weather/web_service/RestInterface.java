package com.vsplc.weather.web_service;

import com.vsplc.weather.web_service.model.Model;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by kundan on 8/8/2015.
 */
public interface RestInterface {

    @GET("/weather")
    void getWheatherReport(@Query("q") String city, Callback<Model>cb);

}
