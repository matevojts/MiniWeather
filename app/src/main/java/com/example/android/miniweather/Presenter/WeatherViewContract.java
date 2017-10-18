package com.example.android.miniweather.Presenter;

import com.example.android.miniweather.Models.CityWeather;

/**
 * Created by matev on 2017. 10. 18..
 */

public interface WeatherViewContract {

    void show(retrofit2.Response<CityWeather> response);

    void error();


}
