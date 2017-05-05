package com.omievee.a9to5.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Admin on 4/27/17.
 */

public interface OpenWeatherService {

    @GET("data/2.5/weather")
    Call<WeatherContainer> getWeather(@Query("APPID") String apiKey, @Query("q") String cityQuery, @Query("units") String units);

}