package com.example.android.miniweather.Network;

import com.example.android.miniweather.Models.CityWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("v1/forecast.json")
    Call<CityWeather> getWeather(@Query("key") String apiKey,
                                 @Query("q") String city,
                                 @Query("days") int days);
}
