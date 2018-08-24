package com.example.android.miniweather.list.presenter

import com.example.android.miniweather.list.network.WeatherService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class WeatherPresenter(private val weatherView: WeatherContract.View) : WeatherContract.UserActions {
    private val apiKey = "a648c3fd154342f1b3190352171310"
    private val forecastDaysRequested = 10
    private val weatherService by lazy { WeatherService.create() }

    override fun getWeatherForCity(cityName: String) {
        weatherView.showLoading()
        weatherService.getWeather(apiKey, cityName, forecastDaysRequested)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { cityWeather ->
                            weatherView.hideLoading()
                            weatherView.showWeather(cityWeather)
                        },
                        { _ ->
                            weatherView.hideLoading()
                            weatherView.error()
                        }

                )
    }

    override fun getWeatherForDefaultCity() {
        weatherView.showLoading()
        weatherService.getWeather(apiKey, "Boston", forecastDaysRequested)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { cityWeather ->
                            weatherView.hideLoading()
                            weatherView.showWeather(cityWeather)
                        },
                        { _ ->
                            weatherView.hideLoading()
                            weatherView.error()
                        }
                )
    }

    override fun openSettings() {
        Timber.d("Open Settings")
//        NavigationManager.moveToScreen(fragmentManager, SettingsFragment())
    }
}
