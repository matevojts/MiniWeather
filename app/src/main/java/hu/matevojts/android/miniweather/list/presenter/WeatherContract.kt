package hu.matevojts.android.miniweather.list.presenter

import hu.matevojts.android.miniweather.list.model.CityForeCast

interface WeatherContract {

    interface View {
        fun hideLoading()
        fun showLoading()
        fun showForecast(cityForeCast: CityForeCast)
        fun error()
    }

    interface UserActions {
        fun getWeatherForCity(cityName: String)
        fun getWeatherForDefaultCity()
        fun openSettings()
    }

}
