package com.example.android.miniweather.View

import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.android.miniweather.Adapter.ForeCastDayAdapter
import com.example.android.miniweather.Manager.NavigationManager
import com.example.android.miniweather.Models.CityWeather
import com.example.android.miniweather.Models.FavouriteCityModel
import com.example.android.miniweather.Models.TemperatureUnit
import com.example.android.miniweather.Presenter.SettingsPresenter
import com.example.android.miniweather.Presenter.WeatherPresenter
import com.example.android.miniweather.Presenter.WeatherViewContract
import com.example.android.miniweather.R
import com.example.android.miniweather.Utils.KeyboardUtils
import kotlinx.android.synthetic.main.weather_view_fragment.*
import retrofit2.Response

class WeatherViewFragment : Fragment(), WeatherViewContract {

    private var foreCastDayAdapter: ForeCastDayAdapter? = null
    private var temperatureUnitModel: TemperatureUnit? = null
    private var favouriteCityModel: FavouriteCityModel? = null
    private var cityWeather: CityWeather? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.weather_view_fragment, container, false)!!


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recycleview.setHasFixedSize(true)
        recycleview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        setHasOptionsMenu(true)

        val weatherPresenter = WeatherPresenter(this)
        val settingsPresenter = SettingsPresenter(this)
        settingsPresenter.saveTemperatureUnitToModel(activity)
        settingsPresenter.saveFavouriteCityModel(activity)


        city_search_button.setOnClickListener { view ->
            val cityName = city_edit_text.text.toString()

            if (cityName.isNotEmpty()) {
                city_country_text_view.text = ""
                KeyboardUtils.hideKeyboard(view, activity)
                weatherPresenter.getWeatherData(cityName)
                city_edit_text.setText("")
            } else {
                Toast.makeText(activity.applicationContext,
                        resources.getString(R.string.city_edittext_empty_message),
                        Toast.LENGTH_LONG).show()
            }
        }

        favourite_button.setOnClickListener { view ->

            city_country_text_view.text = ""
            KeyboardUtils.hideKeyboard(view, activity)
            weatherPresenter.getWeatherData(favouriteCityModel!!.favouriteCity)
            city_edit_text.setText("")
        }
    }

    override fun show(response: Response<CityWeather>) {
        cityWeather = response.body()

        city_country_text_view.text = getString(R.string.city_country,
                cityWeather!!.location.name,
                cityWeather!!.location.country)

        // TODO: check after kotlin refactor, it's only temporary solution now (multiple adapter instantiation)

        foreCastDayAdapter = ForeCastDayAdapter(
                cityWeather!!.forecast.forecastday,
                temperatureUnitModel!!)
        recycleview.adapter = foreCastDayAdapter
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
        return if (item.itemId == R.id.action_settings) {
            NavigationManager.moveToScreen(fragmentManager, SettingsFragment())
            true
        } else {
            false
        }
    }
}
