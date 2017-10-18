package com.example.android.miniweather.Presenter;

import com.example.android.miniweather.Models.CityWeather;
import com.example.android.miniweather.Network.WeatherService;
import com.example.android.miniweather.View.WeatherViewFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by matev on 2017. 10. 18..
 */

public class WeatherPresenter {

    final String APIKEY = "a648c3fd154342f1b3190352171310";
    final int NUMBERS_OF_DAYS_REQUESTED = 10;

    WeatherViewContract viewContract;

    public WeatherPresenter(WeatherViewFragment weatherViewFragment) {
        this.viewContract = weatherViewFragment;
    }

    public void getWeatherData(String cityName) {

        final WeatherService weatherService = new WeatherService();

        weatherService.getService().getWeather(APIKEY, cityName, NUMBERS_OF_DAYS_REQUESTED).enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.isSuccessful()) {
                    viewContract.show(response);
                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                viewContract.error();
            }

        });
    }

}
