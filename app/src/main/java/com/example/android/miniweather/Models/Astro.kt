package com.example.android.miniweather.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Astro(
    @SerializedName("sunrise") @Expose val sunrise: String,
    @SerializedName("sunset") @Expose val sunset: String
)

//{
//
////    @SerializedName("sunrise")
////    @Expose
////    var sunrise: String? = null
//    @SerializedName("sunset")
//    @Expose
//    var sunset: String? = null
//
//}