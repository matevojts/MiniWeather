package com.example.android.miniweather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Day(@SerializedName("maxtemp_c") @Expose val maxtempC: Double,
               @SerializedName("maxtemp_f") @Expose val maxtempF: Double,
               @SerializedName("mintemp_c") @Expose val mintempC: Double,
               @SerializedName("mintemp_f") @Expose val mintempF: Double,
               @SerializedName("condition") @Expose val condition: Condition)