package com.example.android.miniweather.Presenter;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.android.miniweather.Models.FavouriteCityModel;
import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.R;
import com.example.android.miniweather.View.SettingsFragment;
import com.example.android.miniweather.View.WeatherViewFragment;

/**
 * Created by matev on 2017. 11. 30..
 */

public class SettingsPresenter {

    private WeatherViewContract weatherViewContract;

    public SettingsPresenter(WeatherViewFragment weatherViewFragment) {
        this.weatherViewContract = weatherViewFragment;

    }

    public void saveTemperatureUnitToModel(Context context) {
        TemperatureUnit temperatureUnit;
        if (getCurrentTemperatureUnit(context)
                .equals(context.getString(R.string.settings_temperature_celsius_value))){
            temperatureUnit = new TemperatureUnit(true);
        } else {
            temperatureUnit = new TemperatureUnit(false);
        }
        weatherViewContract.saveCurrentTemperature(temperatureUnit);
    }

    private String getCurrentTemperatureUnit(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_temperature_unit_key),
                        context.getString(R.string.settings_temperature_unit_default));
    }

    public void saveFavouriteCityModel(Context context) {
        FavouriteCityModel favouriteCityModel =
                new FavouriteCityModel(getCurrentFavouriteCity(context));
        weatherViewContract.saveCurrentFavouriteCity(favouriteCityModel);
    }

    private String getCurrentFavouriteCity(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_favourite_city_key),
                        context.getString(R.string.settings_favourite_city_default));
    }
}
