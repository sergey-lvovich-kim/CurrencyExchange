package com.mikyegresl.currencyexchange.util

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

class SpinnerExt {
    fun Spinner.setOnItemPickedListener(action: () -> Unit) {
        this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                action()
            }
        }
    }
}