package hu.matevojts.android.miniweather.settings.ui

import android.os.Bundle

import android.preference.PreferenceManager
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import hu.matevojts.android.miniweather.R

class SettingsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_main)

        val favouriteCity = findPreference(getString(R.string.settings_favourite_city_key))
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        favouriteCity.onPreferenceChangeListener = this
        onPreferenceChange(favouriteCity, sharedPreferences.getString(favouriteCity.key, ""))
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        val value: String = newValue.toString()
        preference!!.summary = value
        return true
    }
}
