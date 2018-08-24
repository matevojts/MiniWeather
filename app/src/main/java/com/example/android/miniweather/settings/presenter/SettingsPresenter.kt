package com.example.android.miniweather.settings.presenter

import android.content.Context
import android.preference.PreferenceManager
import com.example.android.miniweather.R

class SettingsPresenter() {

    private fun getCurrentTemperatureUnit(context: Context): String {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_temperature_unit_key),
                        context.getString(R.string.settings_temperature_unit_default))
    }

    private fun getCurrentFavouriteCity(context: Context): String {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_favourite_city_key),
                        context.getString(R.string.settings_favourite_city_default))
    }
}
