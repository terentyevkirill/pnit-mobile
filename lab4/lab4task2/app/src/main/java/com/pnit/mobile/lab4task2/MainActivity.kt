package com.pnit.mobile.lab4task2

import android.content.res.Configuration
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity(), MenuFragment.OnFragmentInteractionListener {
    private lateinit var manager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = supportFragmentManager
        manager.beginTransaction()
            .add(R.id.menu_container, MenuFragment(), "menu_fragment")
            .commit()
    }

    override fun onFragmentInteraction(result: String?) {
        Log.d("onFragmentInteraction", "$result")
        if (requestedOrientation == Configuration.ORIENTATION_PORTRAIT) {
            manager.beginTransaction()
                .replace(R.id.details_container, DetailsFragment.newInstance(result!!), "details_fragment")
                .addToBackStack(null)
                .commit()
        } else {
            // ORIENTATION_LANDSCAPE
            manager.beginTransaction()
                .add(R.id.details_container, DetailsFragment.newInstance(result!!), "details_fragment")
                .commit()
        }

    }


}
