package com.example.android.miniweather.Presenter;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.R;
import com.example.android.miniweather.View.SettingsFragment;

/**
 * Created by matev on 2017. 11. 30..
 */

public class SettingsPresenter {

    SettingsDataPresenter settingsDataPresenter;

    public SettingsPresenter(SettingsFragment settingsFragment) {
        this.settingsDataPresenter = settingsFragment;
    }

    public void saveTemperatureUnitToModel(Context context){
        TemperatureUnit temperatureUnit = new TemperatureUnit();
        if (getCurrentTemperatureUnit(context).equals(context.getString(R.string.settings_temperature_celsius_value))){
            temperatureUnit.setTemperatureUnitToCelsius(true);
        } else {
            temperatureUnit.setTemperatureUnitToCelsius(false);
        }
        settingsDataPresenter.showCurrentTemperatureUnit(temperatureUnit);
    }

    public String getCurrentTemperatureUnit(Context context) {
        return PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.settings_temperature_unit_key),
                        context.getString(R.string.settings_temperature_unit_default));
    }
}
