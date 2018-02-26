package com.example.android.miniweather.Network

import com.example.android.miniweather.Models.CityWeather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    fun getWeather(@Query("key") apiKey: String,
                   @Query("q") city: String,
                   @Query("days") days: Int): Call<CityWeather>
}
