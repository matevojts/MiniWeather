package com.example.android.miniweather.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.android.miniweather.Models.Forecastday
import com.example.android.miniweather.Models.TemperatureUnit
import com.example.android.miniweather.R
import com.squareup.picasso.Picasso

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ForeCastDayAdapter : RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder>() {

    private var forecasts: List<Forecastday>? = null
    private var context: Context? = null
    private var temperatureUnitModel: TemperatureUnit? = null


    fun setForecasts(forecasts: List<Forecastday>) {
        this.forecasts = forecasts
    }

    fun setTemperatureUnitModel(temperatureUnitModel: TemperatureUnit) {
        this.temperatureUnitModel = temperatureUnitModel
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualForecastday = forecasts!![position]

        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(actualForecastday.date)
            val dateWithDay = SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.US).format(date)
            holder.dateTextView.text = dateWithDay
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        holder.sunRiseTextView.text = actualForecastday.astro.sunrise
        holder.sunSetTextView.text = actualForecastday.astro.sunset

        if (temperatureUnitModel!!.isCelsius) {
            holder.maxTempTextView.text = actualForecastday.day.maxtempCelsiusText
            holder.minTempTextView.text = actualForecastday.day.mintempCelsiusText
        } else {
            holder.maxTempTextView.text = actualForecastday.day.maxtempFahrenheitText
            holder.minTempTextView.text = actualForecastday.day.mintempFahrenheitText
        }

        Picasso.with(holder.weatherConditionImageView.context)
                .load(actualForecastday.day.condition
                        .iconURL)
                .placeholder(R.drawable.ic_image_grey600_48dp)
                .fit()
                .centerCrop()
                .into(holder.weatherConditionImageView)
    }

    override fun getItemCount(): Int {
        return forecasts!!.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var weatherConditionImageView: ImageView
        var dateTextView: TextView
        var sunRiseTextView: TextView
        var maxTempTextView: TextView
        var sunSetTextView: TextView
        var minTempTextView: TextView

        init {
            dateTextView = view.findViewById(R.id.date_text_view)
            sunRiseTextView = view.findViewById(R.id.sunrise_text_view)
            maxTempTextView = view.findViewById(R.id.maxtemp_text_view)
            sunSetTextView = view.findViewById(R.id.sunset_text_view)
            minTempTextView = view.findViewById(R.id.mintemp_text_view)
            weatherConditionImageView = view.findViewById(R.id.weather_condition_image_view)
        }
    }
}