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
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

//TODO ez így jól van tördelve?
class ForeCastDayAdapter(val forecasts: List<Forecastday>,
                         val context: Context,
                         val temperatureUnitModel: TemperatureUnit)
    : RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(listItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualForecastday = forecasts[position]

        try {
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(actualForecastday.date)
            val dateWithDay = SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.US).format(date)
            holder.dateTextView.text = dateWithDay
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        holder.sunRiseTextView.text = actualForecastday.astro.sunrise
        holder.sunSetTextView.text = actualForecastday.astro.sunset

        if (temperatureUnitModel.isCelsius) {
            holder.maxTempTextView.text =
                    context.getString(R.string.temperature_in_celsius, actualForecastday.day.maxtempC)
            holder.minTempTextView.text =
                    context.getString(R.string.temperature_in_celsius, actualForecastday.day.mintempC)
        } else {
            holder.maxTempTextView.text =
                    context.getString(R.string.temperature_in_fahrenheit, actualForecastday.day.maxtempF)
            holder.minTempTextView.text =
                    context.getString(R.string.temperature_in_fahrenheit, actualForecastday.day.mintempF)
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
        return forecasts.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val weatherConditionImageView: ImageView = view.weather_condition_image_view
        val dateTextView: TextView = view.date_text_view
        val sunRiseTextView: TextView = view.sunrise_text_view
        val maxTempTextView: TextView = view.maxtemp_text_view
        val sunSetTextView: TextView = view.sunset_text_view
        val minTempTextView: TextView = view.mintemp_text_view
    }
}