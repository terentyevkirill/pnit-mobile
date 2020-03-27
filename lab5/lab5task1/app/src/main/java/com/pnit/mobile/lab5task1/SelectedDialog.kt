package com.pnit.mobile.lab5task1

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

private const val ARG_NUMBER = "number"

class SelectedDialog : DialogFragment() {
    private var number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            number = it.getInt(ARG_NUMBER)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Number")
        builder.setMessage("You chose number: $number")
        builder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
            dismiss()
        }
        return builder.create()
    }

    companion object {
        @JvmStatic
        fun newInstance(number: Int) =
            SelectedDialog().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NUMBER, number)
                }
            }
    }
}
