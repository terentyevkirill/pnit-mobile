package com.pnit.android.lab1

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        but_add.setOnClickListener { v ->
            hideKeyboard()
            var arg1: Double? = null
            var arg2: Double? = null
            try {
                arg1 = edittext_arg1.text.toString().toDouble()
                arg2 = edittext_arg2.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Snackbar.make(v, "Wrong arguments!", Snackbar.LENGTH_SHORT).show()
            }
            if ((arg1 != null) and (arg2 != null)) {
                val result =
                    edittext_arg1.text.toString().toDouble() + edittext_arg2.text.toString().toDouble()
                if ((result - result.toInt()) == 0.0) {
                    textview_result.text = result.toInt().toString()
                } else {
                    textview_result.text = result.toString()
                }
            }
        }

        but_subtr.setOnClickListener { v ->
            hideKeyboard()
            var arg1: Double? = null
            var arg2: Double? = null
            try {
                arg1 = edittext_arg1.text.toString().toDouble()
                arg2 = edittext_arg2.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Snackbar.make(v, "Wrong arguments!", Snackbar.LENGTH_SHORT).show()
            }
            if ((arg1 != null) and (arg2 != null)) {
                val result =
                    edittext_arg1.text.toString().toDouble() - edittext_arg2.text.toString().toDouble()
                if ((result - result.toInt()) == 0.0) {
                    textview_result.text = result.toInt().toString()
                } else {
                    textview_result.text = result.toString()
                }
            }

        }

        but_mult.setOnClickListener { v ->
            hideKeyboard()
            var arg1: Double? = null
            var arg2: Double? = null
            try {
                arg1 = edittext_arg1.text.toString().toDouble()
                arg2 = edittext_arg2.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Snackbar.make(v, "Wrong arguments!", Snackbar.LENGTH_SHORT).show()
            }
            if ((arg1 != null) and (arg2 != null)) {
                val result =
                    edittext_arg1.text.toString().toDouble() * edittext_arg2.text.toString().toDouble()
                if ((result - result.toInt()) == 0.0) {
                    textview_result.text = result.toInt().toString()
                } else {
                    textview_result.text = result.toString()
                }
            }
        }

        but_div.setOnClickListener { v ->
            hideKeyboard()
            var arg1: Double? = null
            var arg2: Double? = null
            try {
                arg1 = edittext_arg1.text.toString().toDouble()
                arg2 = edittext_arg2.text.toString().toDouble()
            } catch (e: NumberFormatException) {
                Snackbar.make(v, "Wrong arguments!", Snackbar.LENGTH_SHORT).show()
            }
            if (arg2 == 0.0) {
                Snackbar.make(v, "Second argument can't be 0", Snackbar.LENGTH_SHORT).show()
            } else if ((arg1 != null) and (arg2 != null)) {
                val result =
                    edittext_arg1.text.toString().toDouble() / edittext_arg2.text.toString().toDouble()
                if ((result - result.toInt()) == 0.0) {
                    textview_result.text = result.toInt().toString()
                } else {
                    textview_result.text = result.toString()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
    }
}
