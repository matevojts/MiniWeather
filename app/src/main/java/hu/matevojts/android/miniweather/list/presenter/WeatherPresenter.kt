package hu.matevojts.android.miniweather.list.presenter

import hu.matevojts.android.miniweather.list.model.CityForeCast
import hu.matevojts.android.miniweather.list.network.WeatherService
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
            .map { CityForeCast(it, true) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cityForecast ->
                    weatherView.hideLoading()
                    weatherView.showForecast(cityForecast)
                },
                { _ ->
                    weatherView.hideLoading()
                    weatherView.error()
                }

            )
    }

    override fun getWeatherForDefaultCity() {
        getWeatherForCity("Boston")
    }

    override fun openSettings() {
        Timber.d("Open Settings")
//        NavigationManager.moveToScreen(fragmentManager, SettingsFragment())
    }
}
