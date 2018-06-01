package com.example.android.miniweather.Presenter

import android.content.Context
import android.preference.PreferenceManager
import com.example.android.miniweather.Models.FavouriteCityModel
import com.example.android.miniweather.Models.TemperatureUnit
import com.example.android.miniweather.R
import com.example.android.miniweather.View.WeatherViewFragment

class SettingsPresenter(weatherViewFragment: WeatherViewFragment) {

    private val weatherViewContract: WeatherViewContract

    init {
        this.weatherViewContract = weatherViewFragment

    }

    fun saveTemperatureUnitToModel(context: Context) {
        val temperatureUnit: TemperatureUnit
        if (getCurrentTemperatureUnit(context) == context.getString(R.string.settings_temperature_celsius_value)) {
            temperatureUnit = TemperatureUnit(true)
        } else {
            temperatureUnit = TemperatureUnit(false)
        }
        weatherViewContract.saveCurrentTemperature(temperatureUnit)
    }

    private fun getCurrentTemperatureUnit(context: Context): String {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_temperature_unit_key),
                        context.getString(R.string.settings_temperature_unit_default))
    }

    fun saveFavouriteCityModel(context: Context) {
        val favouriteCityModel = FavouriteCityModel(getCurrentFavouriteCity(context))
        weatherViewContract.saveCurrentFavouriteCity(favouriteCityModel)
    }

    private fun getCurrentFavouriteCity(context: Context): String {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_favourite_city_key),
                        context.getString(R.string.settings_favourite_city_default))
    }
}
