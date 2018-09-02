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
import com.example.android.miniweather.hide
import com.example.android.miniweather.list.model.CityWeather
import com.example.android.miniweather.list.presenter.WeatherContract
import com.example.android.miniweather.list.presenter.WeatherPresenter
import com.example.android.miniweather.show
import kotlinx.android.synthetic.main.weather_view_fragment.*

class WeatherViewFragment : Fragment(), WeatherContract.View {
    private var foreCastDayAdapter = ForeCastDayAdapter()
    private val presenter = WeatherPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.weather_view_fragment, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        forecastRecyclerView.setHasFixedSize(true)
        forecastRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        forecastRecyclerView.adapter = foreCastDayAdapter
        setHasOptionsMenu(true)
        citySearchButton.setOnClickListener { getWeather() }
        favouriteButton.setOnClickListener { getWeatherForDefaultCity() }
    }

    private fun getWeather() {
        cityEditText.text.toString().apply {
            if (this.isNotBlank()) {
                labelTextView.text = ""
                hideKeyboard()
                presenter.getWeatherForCity(this)
            } else {
                Toast.makeText(
                    context,
                    context!!.getString(R.string.city_edittext_empty_message),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun getWeatherForDefaultCity() {
        labelTextView.text = ""
        hideKeyboard()
        presenter.getWeatherForDefaultCity()
    }

    override fun showWeather(cityWeather: CityWeather) {
        cityEditText.setText("")
        labelTextView.text = getString(
            R.string.city_country,
            cityWeather.location.name,
            cityWeather.location.country
        )
        foreCastDayAdapter.forecasts = cityWeather.forecast.forecastday
        // foreCastDayAdapter.isCelsius = temperatureUnitModel.isCelsius
    }

    override fun error() {
        Toast.makeText(context!!,
            context!!.getString(R.string.api_call_failure_message),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.action_settings) {
            presenter.openSettings()
            true
        } else false
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
        forecastRecyclerView.adapter = null
    }

}
