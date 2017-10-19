package com.example.android.miniweather.Presenter;

import com.example.android.miniweather.Models.CityWeather;

public interface WeatherViewContract {

    void show(retrofit2.Response<CityWeather> response);

    void error();

}
