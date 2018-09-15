package hu.matevojts.android.miniweather.list.presenter

import hu.matevojts.android.miniweather.list.model.CityWeather

interface WeatherContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showWeather(cityWeather: CityWeather)
        fun error()
    }

    interface UserActions {
        fun getWeatherForCity(cityName: String)
        fun getWeatherForDefaultCity()
        fun openSettings()
    }

}
