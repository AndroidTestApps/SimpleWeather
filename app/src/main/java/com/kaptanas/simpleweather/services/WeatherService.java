package com.kaptanas.simpleweather.services;

import com.kaptanas.simpleweather.model.Forecast;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by cihankaptan on 18/02/15.
 */
public interface WeatherService {

    @GET("/{lat},{lon}")
    void getCurrentWeather(@Path("lat") String lat, @Path("lon") String lon, Callback<Forecast> currentlyResponse);
}
