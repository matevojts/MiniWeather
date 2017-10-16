package com.example.android.miniweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.miniweather.Network.WeatherService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.city_country_text_view) TextView cityCountryTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String APIKEY = "a648c3fd154342f1b3190352171310";
        final int NUMBERSOFDAYSREQUESTED = 10;

        recyclerView = (RecyclerView) findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        WeatherService weatherService = new WeatherService();

        weatherService.getService().getWeather(APIKEY, "London", NUMBERSOFDAYSREQUESTED).enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.isSuccessful()) {
                    CityWeather cityWeather = response.body();

                    ButterKnife.bind(MainActivity.this);

                    cityCountryTextView.setText(cityWeather.getLocation().getName() + " - " + cityWeather.getLocation().getCountry());

                    Forecast forecast = cityWeather.getForecast();
                    adapter = new ForeCastDayAdapter(forecast.getForecastday());
                    recyclerView.setAdapter(adapter);

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