package com.pnit.mobile.lab5task3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var stateReceiver: PhoneStateReceiver
    private lateinit var notificationReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        notificationReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val action = intent!!.getStringExtra("action")!!
                val dialog = PhoneStateDialog.newInstance(action)
                dialog.show(supportFragmentManager, "dialog")
            }

        }
        stateReceiver = PhoneStateReceiver()
        val filter = IntentFilter()
        filter.addAction("android.intent.action.BATTERY_LOW")
        filter.addAction("android.intent.action.CAMERA_BUTTON")
        filter.addAction("android.intent.action.AIRPLANE_MODE")

        registerReceiver(stateReceiver, filter)
        registerReceiver(notificationReceiver, IntentFilter("PhoneStateChanged"))
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(stateReceiver)
    }
}