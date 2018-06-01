package com.example.android.miniweather.Presenter

import com.example.android.miniweather.Models.CityWeather
import com.example.android.miniweather.Models.FavouriteCityModel
import com.example.android.miniweather.Models.TemperatureUnit

interface WeatherViewContract {

    fun show(cityWeather: CityWeather)

    fun error()

    fun saveCurrentTemperature(temperatureUnitModel: TemperatureUnit)

    fun saveCurrentFavouriteCity(favouriteCityModel: FavouriteCityModel)

}
