package com.example.android.miniweather.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Location(@SerializedName("name") @Expose val name: String,
                    @SerializedName("country") @Expose val country: String)