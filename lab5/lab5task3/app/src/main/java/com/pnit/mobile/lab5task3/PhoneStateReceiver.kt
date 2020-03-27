package com.pnit.mobile.lab5task3

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log.d

class PhoneStateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        d("PhoneStateListener", "onReceive: ${intent!!.action!!}")
        val i = Intent("PhoneStateChanged")
        when {
            intent.action.equals("android.intent.action.BATTERY_LOW") -> {
                d("PhoneStateListener", "BATTERY_LOW")
                i.putExtra("action", "BATTERY_LOW")
                context!!.sendBroadcast(i)
            }
            intent.action.equals("android.intent.action.CAMERA_BUTTON") -> {
                d("PhoneStateListener", "CAMERA_BUTTON")
                i.putExtra("action", "CAMERA_BUTTON")
                context!!.sendBroadcast(i)
            }
            intent.action.equals("android.intent.action.AIRPLANE_MODE") -> {
                if (intent.getBooleanExtra("state", false)) {
                    d("PhoneStateListener", "AIRPLANE_MODE_ON")
                    i.putExtra("action", "AIRPLANE_MODE_ON")
                } else {
                    d("PhoneStateListener", "AIRPLANE_MODE_OFF")
                    i.putExtra("action", "AIRPLANE_MODE_OFF")
                }
                context!!.sendBroadcast(i)
            }
        }
    }
}