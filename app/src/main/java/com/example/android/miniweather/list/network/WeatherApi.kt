package com.example.android.miniweather.list.network

import com.example.android.miniweather.list.model.CityWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast.json")
    fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String,
        @Query("days") days: Int
    ): Observable<CityWeather>
}
