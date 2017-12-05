package com.example.android.miniweather.Manager;

import android.app.Fragment;
import android.app.FragmentManager;


/**
 * Created by matev on 2017. 11. 30..
 */

public class NavigationManager {

    public static void moveToScreen (FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static void moveToFirstScreen (FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(android.R.id.content, fragment)
                .commit();
    }
}
