package com.example.android.miniweather.presenter

import com.example.android.miniweather.model.CityWeather
import com.example.android.miniweather.model.FavouriteCityModel
import com.example.android.miniweather.model.TemperatureUnit

interface WeatherViewContract {

    fun show(cityWeather: CityWeather)

    fun error()

    fun saveCurrentTemperature(temperatureUnitModel: TemperatureUnit)

    fun saveCurrentFavouriteCity(favouriteCityModel: FavouriteCityModel)

}
