package com.example.android.miniweather.presenter

import com.example.android.miniweather.model.CityWeather
import com.example.android.miniweather.network.WeatherService
import com.example.android.miniweather.ui.WeatherViewFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPresenter(weatherViewFragment: WeatherViewFragment) {

    private val APIKEY = "a648c3fd154342f1b3190352171310"
    private val NUMBERS_OF_DAYS_REQUESTED = 10
    private val viewContract: WeatherViewContract

    init {
        this.viewContract = weatherViewFragment
    }

    fun getWeatherData(cityName: String) {

        val weatherService = WeatherService()

        weatherService.service.getWeather(APIKEY, cityName, NUMBERS_OF_DAYS_REQUESTED).enqueue(object : Callback<CityWeather> {

            override fun onResponse(call: Call<CityWeather>, response: Response<CityWeather>) {
                if (response.isSuccessful) {
                    viewContract.show(response.body()!!)
                }
            }

            override fun onFailure(call: Call<CityWeather>, t: Throwable) {
                viewContract.error()
            }

        })
    }
}