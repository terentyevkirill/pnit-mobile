package com.pnit.mobile.lab5task2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import android.telephony.SmsMessage
import android.widget.Toast

class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals("android.provider.Telephony.SMS_RECEIVED")) {
            for (sms: SmsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
                val msg = "From ${sms.originatingAddress} : ${sms.messageBody}"
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
            }
        }
    }
}