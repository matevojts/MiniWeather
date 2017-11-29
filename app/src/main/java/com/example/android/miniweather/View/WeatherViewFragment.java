package com.example.android.miniweather.View;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.miniweather.Adapter.ForeCastDayAdapter;
import com.example.android.miniweather.Models.CityWeather;
import com.example.android.miniweather.Models.Forecast;
import com.example.android.miniweather.Presenter.WeatherPresenter;
import com.example.android.miniweather.Presenter.WeatherViewContract;
import com.example.android.miniweather.R;

import retrofit2.Response;

public class WeatherViewFragment extends Fragment implements WeatherViewContract{

    TextView cityCountryTextView;
    EditText cityEditText;
    Button citySearchButton;
    View view;
    String cityName;

    Forecast forecast = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_view_fragment, container, false);
        init();
        return view;
    }

    private void init(){
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        cityCountryTextView = view.findViewById(R.id.city_country_text_view);
        cityEditText = view.findViewById(R.id.city_edit_text);
        citySearchButton = view.findViewById(R.id.city_search_button);

        setHasOptionsMenu(true);

        final WeatherPresenter presenter = new WeatherPresenter(this);

        forecast = new Forecast();

        citySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (forecast.getForecastday() != null){
                    forecast.getForecastday().clear();
                    adapter.notifyDataSetChanged();
                    cityCountryTextView.setText("");
                }

                cityName = cityEditText.getText().toString();
                presenter.getWeatherData(cityName);
            }
        });

    }

    @Override
    public void show(Response<CityWeather> response) {
        CityWeather cityWeather = response.body();
        cityCountryTextView.setText(cityWeather.getLocation().getName() + " - " + cityWeather.getLocation().getCountry());

        forecast = cityWeather.getForecast();
                            adapter = new ForeCastDayAdapter(forecast.getForecastday());
                            recyclerView.setAdapter(adapter);
    }

    @Override
    public void error() {
        Toast.makeText(getActivity().getApplicationContext(), getResources().getString(R.string.api_call_failure_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            //TODO: exchange toast with logic
            Toast.makeText(getActivity().getApplicationContext(), "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return false;
        }
    }

}
