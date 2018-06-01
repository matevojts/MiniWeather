package com.example.android.miniweather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Forecastday(@SerializedName("date") @Expose val date: String,
                       @SerializedName("day") @Expose val day: Day,
                       @SerializedName("astro") @Expose val astro: Astro)