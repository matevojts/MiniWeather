package hu.matevojts.android.miniweather

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import hu.matevojts.android.miniweather.R
import hu.matevojts.android.miniweather.list.ui.WeatherViewFragment
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val weatherViewFragment = WeatherViewFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.plant(Timber.DebugTree())
        NavigationManager.moveToFirstScreen(supportFragmentManager, weatherViewFragment)
    }

}
