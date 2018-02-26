package com.example.android.miniweather.View

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import com.example.android.miniweather.Adapter.ForeCastDayAdapter
import com.example.android.miniweather.Manager.NavigationManager
import com.example.android.miniweather.Models.CityWeather
import com.example.android.miniweather.Models.FavouriteCityModel
import com.example.android.miniweather.Models.Forecast
import com.example.android.miniweather.Models.TemperatureUnit
import com.example.android.miniweather.Presenter.SettingsPresenter
import com.example.android.miniweather.Presenter.WeatherPresenter
import com.example.android.miniweather.Presenter.WeatherViewContract
import com.example.android.miniweather.R
import com.example.android.miniweather.Utils.KeyboardUtils

import retrofit2.Response

class WeatherViewFragment : Fragment(), WeatherViewContract {

    internal var cityCountryTextView: TextView
    internal var cityEditText: EditText
    internal var citySearchButton: Button
    internal var favouriteButton: ImageButton
    internal var view: View
    internal var cityName: String
    internal var forecast: Forecast? = null
    private var recyclerView: RecyclerView? = null
    private var foreCastDayAdapter: ForeCastDayAdapter? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var temperatureUnitModel: TemperatureUnit? = null
    private var favouriteCityModel: FavouriteCityModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.weather_view_fragment, container, false)
        init()
        return view
    }

    private fun init() {
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView = view.findViewById(R.id.recycleview)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = layoutManager

        cityCountryTextView = view.findViewById(R.id.city_country_text_view)
        cityEditText = view.findViewById(R.id.city_edit_text)
        citySearchButton = view.findViewById(R.id.city_search_button)
        favouriteButton = view.findViewById(R.id.favourite_button)

        setHasOptionsMenu(true)

        val presenter = WeatherPresenter(this)
        val settingsPresenter = SettingsPresenter(this)
        settingsPresenter.saveTemperatureUnitToModel(activity)
        settingsPresenter.saveFavouriteCityModel(activity)

        forecast = Forecast()

        citySearchButton.setOnClickListener { view ->
            cityName = cityEditText.text.toString()

            if (!cityName.matches("".toRegex())) {
                if (forecast!!.forecastday != null) {
                    forecast!!.forecastday.clear()
                    cityCountryTextView.text = ""
                }

                KeyboardUtils.hideKeyboard(view, activity)
                presenter.getWeatherData(cityName)
                cityEditText.setText("")
            }
        }

        favouriteButton.setOnClickListener { view ->
            if (forecast!!.forecastday != null) {
                forecast!!.forecastday.clear()
                cityCountryTextView.text = ""
            }
            KeyboardUtils.hideKeyboard(view, activity)
            presenter.getWeatherData(favouriteCityModel!!.favouriteCity)
            cityEditText.setText("")
        }

    }

    override fun show(response: Response<CityWeather>) {
        val cityWeather = response.body()

        cityCountryTextView.text = getString(R.string.city_country,
                cityWeather!!.location.name,
                cityWeather.location.country)

        forecast = cityWeather.forecast

        // TODO: check after kotlin refactor, it's only temporary solution now (multiple adapter instantiation)

        foreCastDayAdapter = ForeCastDayAdapter(
                forecast!!.forecastday,
                activity.applicationContext,
                temperatureUnitModel!!)
        recyclerView!!.adapter = foreCastDayAdapter
        foreCastDayAdapter!!.notifyDataSetChanged()

    }

    override fun error() {
        Toast.makeText(activity.applicationContext, resources.getString(R.string.api_call_failure_message), Toast.LENGTH_LONG).show()
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
        if (item.itemId == R.id.action_settings) {
            NavigationManager.moveToScreen(fragmentManager, SettingsFragment())
            return true
        } else {
            return false
        }
    }
}
