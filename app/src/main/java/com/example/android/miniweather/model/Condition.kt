package com.example.android.miniweather.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Condition(@SerializedName("icon") @Expose val icon: String) {

    val iconURL: String
        get() = "http:" + icon
}