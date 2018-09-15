package com.example.android.miniweather

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

object NavigationManager {

    @JvmStatic
    fun moveToScreen(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .addToBackStack(null)
            .commit()
    }

    @JvmStatic
    fun moveToFirstScreen(fragmentManager: FragmentManager, fragment: Fragment) {
        fragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment)
            .commit()
    }
}
