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

    SettingsDataPresenter settingsDataPresenter;
    WeatherViewContract weatherViewContract;

    public SettingsPresenter(SettingsFragment settingsFragment, WeatherViewFragment weatherViewFragment) {
        this.settingsDataPresenter = settingsFragment;
        this.weatherViewContract = weatherViewFragment;

    }

    public void saveTemperatureUnitToModel(Context context) {
        TemperatureUnit temperatureUnit = new TemperatureUnit();
        if (getCurrentTemperatureUnit(context)
                .equals(context.getString(R.string.settings_temperature_celsius_value))){
            temperatureUnit.setTemperatureUnitToCelsius(true);
        } else {
            temperatureUnit.setTemperatureUnitToCelsius(false);
        }
        settingsDataPresenter.showCurrentTemperatureUnit(temperatureUnit);
        weatherViewContract.saveCurrentTemperature(temperatureUnit);
    }

    public String getCurrentTemperatureUnit(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_temperature_unit_key),
                        context.getString(R.string.settings_temperature_unit_default));
    }

    public void saveFavouriteCityModel(Context context) {
        FavouriteCityModel favouriteCityModel = new FavouriteCityModel();
        favouriteCityModel.setFavouriteCity(getCurrentFavouriteCity(context));
        weatherViewContract.saveCurrentFavouriteCity(favouriteCityModel);
    }

    public String getCurrentFavouriteCity(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_favourite_city_key),
                        context.getString(R.string.settings_favourite_city_default));
    }
}
