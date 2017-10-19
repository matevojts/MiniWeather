package com.example.android.miniweather.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.miniweather.Models.Forecastday;
import com.example.android.miniweather.R;
import com.squareup.picasso.Picasso;
import java.util.List;

public class ForeCastDayAdapter extends RecyclerView.Adapter<ForeCastDayAdapter.ViewHolder> {

    private List<Forecastday> forecasts;

    public ForeCastDayAdapter(List<Forecastday> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(listItemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecastday actualForecastday = forecasts.get(position);
        holder.dateTextView.setText(actualForecastday.getDate());
        holder.sunRiseTextView.setText(actualForecastday.getAstro().getSunrise());
        holder.maxTempTextView.setText(actualForecastday.getDay().getMaxtempCelsiusText());
        holder.sunSetTextView.setText(actualForecastday.getAstro().getSunset());
        holder.minTempTextView.setText(actualForecastday.getDay().getMintempCelsiusText());
        Picasso.with(holder.weatherConditionImageView.getContext()).load(actualForecastday.getDay().getCondition().getIconURL()).placeholder(R.drawable.ic_image_grey600_48dp).fit().centerCrop().into(holder.weatherConditionImageView);
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