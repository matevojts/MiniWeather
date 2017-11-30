package com.example.android.miniweather.View;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.Presenter.SettingsDataPresenter;
import com.example.android.miniweather.Presenter.SettingsPresenter;
import com.example.android.miniweather.R;

/**
 * Created by matev on 2017. 11. 27..
 */

public class SettingsFragment extends PreferenceFragment implements SettingsDataPresenter {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);

        //TODO: implement summary

        /*final SettingsPresenter settingsPresenter = new SettingsPresenter(this);
        settingsPresenter.saveTemperatureUnitToModel(getActivity());*/



        /*Preference temperatureUnit = findPreference(getString(R.string.settings_temperature_unit_key));
        bindPreferenceSummaryToValue(temperatureUnit);*/

    }

/*
    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();
        if (preference instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                CharSequence[] labels = listPreference.getEntries();
                preference.setSummary(labels[prefIndex]);
            }
        } else {
            preference.setSummary(stringValue);
        }
        return true;
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());
        String preferenceString = preferences.getString(preference.getKey(), "");
        onPreferenceChange(preference, preferenceString);
    }*/


    @Override
    public void showCurrentTemperatureUnit(TemperatureUnit temperatureUnit) {

        //TODO: DISASTER!!! (but works till tomorrow)
        WeatherViewFragment.temperatureUnit = temperatureUnit;
    }

}
