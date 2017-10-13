package com.example.android.miniweather.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by matev on 2017. 10. 13..
 */

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
