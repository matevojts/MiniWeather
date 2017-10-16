package com.example.android.miniweather;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.miniweather.Network.WeatherService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @BindView(R.id.city_country_text_view) TextView cityCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String apiKey = "a648c3fd154342f1b3190352171310";
        int numberOfDaysRequested = 10;

        WeatherService weatherService = new WeatherService();

        weatherService.getService().getWeather(apiKey, "London", numberOfDaysRequested).enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.isSuccessful()) {
                    CityWeather cityWeather = response.body();
                    Log.i("Location", cityWeather.getLocation().getCountry());
                    Log.i("Sunrise1", cityWeather.getForecast().getForecastday().get(1).getAstro().getSunrise());
                    ButterKnife.bind(MainActivity.this);
                    cityCountryTextView.setText(cityWeather.getLocation().getName() + " - " + cityWeather.getLocation().getCountry());

                    Forecast forecast = new Forecast();

                    mRecyclerView = (RecyclerView) findViewById(R.id.list);
                    mLayoutManager = new LinearLayoutManager(MainActivity.this);
                    mRecyclerView.setLayoutManager(mLayoutManager);
                    mAdapter = new ForeCastDayAdapter(forecast);
                    mRecyclerView.setAdapter(mAdapter);

                }
            }

            @Override
            public void onFailure(Call<CityWeather> call, Throwable t) {
                Log.e("Apicall failed.", t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}

//TODO: http://api.apixu.com/v1/forecast.json?key=a648c3fd154342f1b3190352171310&q=London&days=10