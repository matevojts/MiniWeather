package com.example.android.miniweather.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CityWeather(@SerializedName("location") @Expose val location: Location,
    @SerializedName("forecast") @Expose val forecast: Forecast)