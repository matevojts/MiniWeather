package com.example.android.miniweather.Manager

import android.app.Fragment
import android.app.FragmentManager


/**
 * Created by matev on 2017. 11. 30..
 */

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
