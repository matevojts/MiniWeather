package com.example.android.miniweather.Adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.miniweather.Models.Forecastday
import com.example.android.miniweather.Models.TemperatureUnit
import com.example.android.miniweather.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.util.Locale

class ForeCastDayAdapter(val forecasts: List<Forecastday>, val temperatureUnitModel: TemperatureUnit)
    : RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actualForecastday = forecasts[position]
        holder.bind(actualForecastday, temperatureUnitModel)
    }

    override fun getItemCount() = forecasts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(forecastday: Forecastday, temperatureUnitModel: TemperatureUnit) = with(itemView){
            Picasso.with(itemView.context)
                    .load(forecastday.day.condition.iconURL)
                    .placeholder(R.drawable.ic_image_grey600_48dp)
                    .fit()
                    .centerCrop()
                    .into(itemView.weather_condition_image_view)
            itemView.sunrise_text_view.text = forecastday.astro.sunrise
            itemView.sunset_text_view.text = forecastday.astro.sunset

            if (temperatureUnitModel.isCelsius) {
                itemView.maxtemp_text_view.text =
                        context.getString(R.string.temperature_in_celsius, forecastday.day.maxtempC)
                itemView.mintemp_text_view.text =
                        context.getString(R.string.temperature_in_celsius, forecastday.day.mintempC)
            } else {
                itemView.maxtemp_text_view.text =
                        context.getString(R.string.temperature_in_fahrenheit, forecastday.day.maxtempF)
                itemView.mintemp_text_view.text =
                        context.getString(R.string.temperature_in_fahrenheit, forecastday.day.mintempF)
            }
            //TODO: move dateformating to dto-domain.
            val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(forecastday.date)
            val dateWithDay = SimpleDateFormat("EEEE, yyyy-MM-dd", Locale.getDefault())
            itemView.date_text_view.text = dateWithDay.format(date)
        }
    }
}