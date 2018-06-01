package com.example.android.miniweather.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService {

    private val BASEURL = "http://api.apixu.com/"

    val service: WeatherApi
        get() {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(WeatherApi::class.java)
        }
}
