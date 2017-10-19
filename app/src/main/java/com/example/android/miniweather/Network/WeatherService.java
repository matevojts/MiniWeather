package com.example.android.miniweather.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {

    private final String BASEURL = "http://api.apixu.com/";

    public WeatherApi getService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(WeatherApi.class);
    }
}
