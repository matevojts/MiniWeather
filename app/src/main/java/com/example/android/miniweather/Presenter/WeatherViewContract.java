package com.example.android.miniweather.Presenter;

import com.example.android.miniweather.Models.CityWeather;
import com.example.android.miniweather.Models.TemperatureUnit;

public interface WeatherViewContract {

    void show(retrofit2.Response<CityWeather> response);

    void error();

    void saveCurrentTemperature(TemperatureUnit temperatureUnitModel);

}
