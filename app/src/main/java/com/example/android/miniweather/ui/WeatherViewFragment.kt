package com.example.android.miniweather.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.android.miniweather.ui.adapter.ForeCastDayAdapter
import com.example.android.miniweather.NavigationManager
import com.example.android.miniweather.model.CityWeather
import com.example.android.miniweather.model.FavouriteCityModel
import com.example.android.miniweather.model.TemperatureUnit
import com.example.android.miniweather.presenter.SettingsPresenter
import com.example.android.miniweather.presenter.WeatherPresenter
import com.example.android.miniweather.presenter.WeatherViewContract
import com.example.android.miniweather.R
import kotlinx.android.synthetic.main.weather_view_fragment.*

class WeatherViewFragment : Fragment(), WeatherViewContract {

    private var foreCastDayAdapter: ForeCastDayAdapter? = null
    private var temperatureUnitModel: TemperatureUnit? = null
    private var favouriteCityModel: FavouriteCityModel? = null
    private val weatherPresenter = WeatherPresenter(this)
    private val settingsPresenter = SettingsPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.weather_view_fragment, container, false)!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forecastRecyclerView.setHasFixedSize(true)
        forecastRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setHasOptionsMenu(true)
        settingsPresenter.saveTemperatureUnitToModel(context!!)
        settingsPresenter.saveFavouriteCityModel(context!!)

        citySearchButton.setOnClickListener {
            val cityName = cityEditText.text.toString()
            if (cityName.isNotEmpty()) {
                getWeather(cityName)
            } else {
                Toast.makeText(context, resources.getString(R.string.city_edittext_empty_message), Toast.LENGTH_LONG).show()
            }
        }

        favouriteButton.setOnClickListener {
            getWeather(favouriteCityModel!!.favouriteCity)
        }
    }

    private fun getWeather(cityName: String) {
        labelTextView.text = ""
        hideKeyboard()
        weatherPresenter.getWeatherData(cityName)
        cityEditText.setText("")
    }

    override fun show(cityWeather: CityWeather) {
        labelTextView.text =
                getString(R.string.city_country, cityWeather.location.name, cityWeather.location.country)
        // TODO: check after kotlin refactor, it's only temporary solution now (multiple adapter instantiation)
        foreCastDayAdapter = ForeCastDayAdapter(cityWeather.forecast.forecastday, temperatureUnitModel!!)
        forecastRecyclerView.adapter = foreCastDayAdapter
        foreCastDayAdapter!!.notifyDataSetChanged()
    }

    override fun error() {
        Toast.makeText(context!!, resources.getString(R.string.api_call_failure_message), Toast.LENGTH_LONG).show()
    }

    override fun saveCurrentTemperature(temperatureUnitModel: TemperatureUnit) {
        this.temperatureUnitModel = temperatureUnitModel
    }

    override fun saveCurrentFavouriteCity(favouriteCityModel: FavouriteCityModel) {
        this.favouriteCityModel = favouriteCityModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            NavigationManager.moveToScreen(fragmentManager!!, SettingsFragment())
            true
        } else {
            false
        }
    }

    private fun hideKeyboard() {
        val inputMethodManager = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }
}
