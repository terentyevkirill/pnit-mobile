package com.pnit.mobile.lab5task2

import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestSmsPermission()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestSmsPermission() {
        if (checkSelfPermission(android.Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(android.Manifest.permission.RECEIVE_SMS), 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            var msg = "Permission to receive sms"
            msg += if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                " granted"
            } else {
                " not granted"
            }
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }
    }
}
