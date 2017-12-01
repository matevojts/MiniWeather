package com.example.android.miniweather.View;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.Presenter.SettingsDataPresenter;
import com.example.android.miniweather.R;

/**
 * Created by matev on 2017. 11. 27..
 */

public class SettingsFragment extends PreferenceFragment implements SettingsDataPresenter {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_main);
    }

    @Override
    public void showCurrentTemperatureUnit(TemperatureUnit temperatureUnit) {

    }

}
