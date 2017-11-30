package com.example.android.miniweather.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.miniweather.Models.Forecastday;
import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ForeCastDayAdapter extends RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder> {

    private List<Forecastday> forecasts;
    private Context context;
    TemperatureUnit temperatureUnit;

    public ForeCastDayAdapter(List<Forecastday> forecasts, TemperatureUnit temperatureUnit) {
        this.forecasts = forecasts;
        this.temperatureUnit = temperatureUnit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecastday actualForecastday = forecasts.get(position);
        holder.dateTextView.setText(actualForecastday.getDate());
        holder.sunRiseTextView.setText(actualForecastday.getAstro().getSunrise());
        holder.sunSetTextView.setText(actualForecastday.getAstro().getSunset());


        /*SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        String currentTemperatureUnit = sharedPreferences.getString(context.getString(R.string.settings_temperature_unit_key), "celsius");*/

        if (temperatureUnit.isCelsius()) {
            holder.maxTempTextView.setText(actualForecastday.getDay().getMaxtempCelsiusText());
            holder.minTempTextView.setText(actualForecastday.getDay().getMintempCelsiusText());
        } else {
            holder.maxTempTextView.setText(actualForecastday.getDay().getMaxtempFahrenheitText());
            holder.minTempTextView.setText(actualForecastday.getDay().getMintempFahrenheitText());
        }

        Picasso.with(holder.weatherConditionImageView.getContext())
                .load(actualForecastday.getDay().getCondition()
                .getIconURL())
                .placeholder(R.drawable.ic_image_grey600_48dp)
                .fit()
                .centerCrop()
                .into(holder.weatherConditionImageView);
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView weatherConditionImageView;
        public TextView dateTextView, sunRiseTextView, maxTempTextView, sunSetTextView, minTempTextView;

        public ViewHolder(View view) {
            super(view);
            dateTextView = view.findViewById(R.id.date_text_view);
            sunRiseTextView = view.findViewById(R.id.sunrise_text_view);
            maxTempTextView = view.findViewById(R.id.maxtemp_text_view);
            sunSetTextView = view.findViewById(R.id.sunset_text_view);
            minTempTextView = view.findViewById(R.id.mintemp_text_view);
            weatherConditionImageView = view.findViewById(R.id.weather_condition_image_view);
        }
    }
}