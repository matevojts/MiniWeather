package com.example.android.miniweather.list.presenter

import com.example.android.miniweather.list.model.CityWeather
import com.example.android.miniweather.settings.model.FavouriteCityModel
import com.example.android.miniweather.settings.model.TemperatureUnit

interface WeatherContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showWeather(cityWeather: CityWeather)
        fun error()
    }

    interface UserActions{
        fun getWeatherForCity(cityName: String)
        fun getWeatherForDefaultCity()
        fun openSettings()
    }

}
