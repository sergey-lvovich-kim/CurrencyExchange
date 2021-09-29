package com.mikyegresl.currencyexchange.common

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.mikyegresl.currencyexchange.data.source.RatesRepository
import com.mikyegresl.currencyexchange.util.ConnectivityManager

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    appCompositionRoot: AppCompositionRoot
) {
    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)
    val ratesRepository: RatesRepository = appCompositionRoot.createRatesRepository()
    val connectivityManager: ConnectivityManager get() = ConnectivityManager(activity)
}