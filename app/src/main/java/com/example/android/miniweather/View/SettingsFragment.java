package com.example.android.miniweather.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.android.miniweather.Models.FavouriteCityModel;
import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.Presenter.SettingsDataPresenter;
import com.example.android.miniweather.R;

/**
 * Created by matev on 2017. 11. 27..
 */

public class SettingsFragment extends PreferenceFragment
        implements SettingsDataPresenter, Preference.OnPreferenceChangeListener {

    FavouriteCityModel favouriteCityModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);

        Preference favouriteCity = findPreference(getString(R.string.settings_favourite_city_key));
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(favouriteCity.getContext());
        favouriteCity.setOnPreferenceChangeListener(this);
        onPreferenceChange(favouriteCity, sharedPreferences.getString(favouriteCity.getKey(), ""));

    }

    @Override
    public void showCurrentTemperatureUnit(TemperatureUnit temperatureUnit) {

    }

        @Override
    public boolean onPreferenceChange(Preference preference, Object object) {
        String value = object.toString();
        preference.setSummary(value);
        return true;
    }
}
