package com.example.android.miniweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityWeather {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}