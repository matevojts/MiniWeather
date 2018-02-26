package com.example.android.miniweather.View

import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceManager
import com.example.android.miniweather.R

class SettingsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings_main)

        val favouriteCity = findPreference(getString(R.string.settings_favourite_city_key))
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(favouriteCity.context)
        favouriteCity.onPreferenceChangeListener = this
        onPreferenceChange(favouriteCity, sharedPreferences.getString(favouriteCity.key, ""))

    }

    override fun onPreferenceChange(preference: Preference, `object`: Any?): Boolean {
        val value = `object`!!.toString()
        preference.summary = value
        return true
    }
}
