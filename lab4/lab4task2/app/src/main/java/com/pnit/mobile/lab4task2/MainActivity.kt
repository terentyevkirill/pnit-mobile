package com.pnit.mobile.lab4task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        TODO("Not yet implemented")
    }


}
