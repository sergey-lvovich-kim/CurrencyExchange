package com.mikyegresl.currencyexchange

import android.app.Application
import com.mikyegresl.currencyexchange.common.AppCompositionRoot

class RatesApp(): Application() {
    lateinit var compositionRoot: AppCompositionRoot

    override fun onCreate() {
        super.onCreate()

        compositionRoot = AppCompositionRoot(this)
    }
}