package com.example.android.miniweather.list.ui

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
import com.example.android.miniweather.R
import com.example.android.miniweather.list.model.CityWeather
import com.example.android.miniweather.list.presenter.WeatherContract
import com.example.android.miniweather.list.presenter.WeatherPresenter
import com.example.android.miniweather.settings.model.TemperatureUnit
import kotlinx.android.synthetic.main.weather_view_fragment.*

class WeatherViewFragment : Fragment(), WeatherContract.View {
    private var foreCastDayAdapter: ForeCastDayAdapter? = null
    private val presenter = WeatherPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
            inflater.inflate(R.layout.weather_view_fragment, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forecastRecyclerView.setHasFixedSize(true)
        forecastRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        setHasOptionsMenu(true)

        citySearchButton.setOnClickListener {
            getWeather()
        }

        favouriteButton.setOnClickListener {
            getWeatherForDefaultCity()
        }
    }

    private fun getWeather() {
        val cityName = cityEditText.text.toString()
        if (cityName.isNotBlank()) {
            labelTextView.text = ""
            hideKeyboard()
            presenter.getWeatherForCity(cityName)
        } else {
            Toast.makeText(context, resources.getString(R.string.city_edittext_empty_message), Toast.LENGTH_LONG).show()
        }
    }

    private fun getWeatherForDefaultCity() {
        labelTextView.text = ""
        hideKeyboard()
        presenter.getWeatherForDefaultCity()
    }

    override fun showWeather(cityWeather: CityWeather) {
        cityEditText.setText("")
        labelTextView.text = getString(R.string.city_country,
                cityWeather.location.name,
                cityWeather.location.country
        )
        // TODO: check after kotlin refactor, it's only temporary solution now (multiple adapter instantiation) and provide TempUnit
        foreCastDayAdapter = ForeCastDayAdapter(cityWeather.forecast.forecastday, TemperatureUnit(true))
        forecastRecyclerView.adapter = foreCastDayAdapter
        foreCastDayAdapter!!.notifyDataSetChanged()
    }

    override fun error() {
        Toast.makeText(context!!, resources.getString(R.string.api_call_failure_message), Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            presenter.openSettings()
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
