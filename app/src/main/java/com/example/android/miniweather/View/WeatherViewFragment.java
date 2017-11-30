package com.example.android.miniweather.View;

import android.app.Fragment;
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
import com.example.android.miniweather.Manager.NavigationManager;
import com.example.android.miniweather.Models.CityWeather;
import com.example.android.miniweather.Models.Forecast;
import com.example.android.miniweather.Models.TemperatureUnit;
import com.example.android.miniweather.Presenter.SettingsDataPresenter;
import com.example.android.miniweather.Presenter.SettingsPresenter;
import com.example.android.miniweather.Presenter.WeatherPresenter;
import com.example.android.miniweather.Presenter.WeatherViewContract;
import com.example.android.miniweather.R;

import retrofit2.Response;

public class WeatherViewFragment extends Fragment implements WeatherViewContract, SettingsDataPresenter{

    TextView cityCountryTextView;
    EditText cityEditText;
    Button citySearchButton;
    View view;
    String cityName;

    Forecast forecast = null;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    //TODO: DISASTER!!! (but works till tomorrow)
    public static TemperatureUnit temperatureUnit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.weather_view_fragment, container, false);
        init();
        return view;
    }

    /*@Override
    public void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }*/

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

        final SettingsPresenter settingsPresenter = new SettingsPresenter(new SettingsFragment());
        settingsPresenter.saveTemperatureUnitToModel(getActivity());

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
        cityCountryTextView.setText(cityWeather.getLocation().getName()
                + " - "
                + cityWeather.getLocation().getCountry());

        forecast = cityWeather.getForecast();

        //TODO: remove check
        if (temperatureUnit != null) {
            adapter = new ForeCastDayAdapter(forecast.getForecastday(), temperatureUnit);
        } else {
            Toast.makeText(getActivity(), "Tempunit = null", Toast.LENGTH_SHORT).show();
        }
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
            NavigationManager.moveToScreen(getFragmentManager(), new SettingsFragment());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showCurrentTemperatureUnit(TemperatureUnit temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }
}
