package com.example.android.miniweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.miniweather.Adapter.ForeCastDayAdapter;
import com.example.android.miniweather.Models.CityWeather;
import com.example.android.miniweather.Models.Forecast;
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
    private String cityName;

    @BindView(R.id.city_country_text_view)
    TextView cityCountryTextView;
    @BindView(R.id.city_edit_text)
    EditText cityEditText;
    @BindView(R.id.city_search_button)
    Button citySearchButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_view_fragment); // csak itt valtoztattam meg a kodot az xml-en kivul a fragmenthez



        ButterKnife.bind(MainActivity.this);

        citySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cityName = cityEditText.getText().toString();
            }
        });



        final String APIKEY = "a648c3fd154342f1b3190352171310";
        final int NUMBERSOFDAYSREQUESTED = 10;

        recyclerView = (RecyclerView) findViewById(R.id.list);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        WeatherService weatherService = new WeatherService();

        weatherService.getService().getWeather(APIKEY, "Budapest", NUMBERSOFDAYSREQUESTED).enqueue(new Callback<CityWeather>() {
            @Override
            public void onResponse(Call<CityWeather> call, Response<CityWeather> response) {
                if (response.isSuccessful()) {
                    CityWeather cityWeather = response.body();

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