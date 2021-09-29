package com.mikyegresl.currencyexchange.screens.common

import androidx.appcompat.app.AppCompatActivity
import com.mikyegresl.currencyexchange.RatesApp
import com.mikyegresl.currencyexchange.common.ActivityCompositionRoot

open class BaseActivity(): AppCompatActivity() {
    private val appCompositionRoot get() = (application as RatesApp).compositionRoot
    val activityCompositionRoot by lazy {
        ActivityCompositionRoot(this, appCompositionRoot)
    }
}