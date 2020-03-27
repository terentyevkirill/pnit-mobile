package com.pnit.mobile.lab5task3

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ACTION = "action"

class PhoneStateDialog : DialogFragment() {
    private var action: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            action = it.getString(ARG_ACTION)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        when {
            action.equals("BATTERY_LOW") -> {
                builder.setTitle("Battery low")
                builder.setMessage("Battery low!")
            }
            action.equals("CAMERA_BUTTON") -> {
                builder.setTitle("Camera button")
                builder.setMessage("Camera button pressed!")
            }
            action.equals("AIRPLANE_MODE_ON") -> {
                builder.setTitle("Airplane mode")
                builder.setMessage("Airplane mode ON!")
            }
            action.equals("AIRPLANE_MODE_OFF") -> {
                builder.setTitle("Airplane mode")
                builder.setMessage("Airplane mode OFF!")
            }
        }
        builder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
            dismiss()
        }
        return builder.create()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param action Parameter 1.
         * @return A new instance of fragment PhoneStateDialog.
         */
        @JvmStatic
        fun newInstance(action: String) =
            PhoneStateDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_ACTION, action)
                }
            }
    }
}
