package com.mikyegresl.currencyexchange.common

import com.mikyegresl.currencyexchange.screens.common.ViewModelFactory
import com.mikyegresl.currencyexchange.screens.common.ViewMvcFactory

class PresentationCompositionRoot(private val activityCompositionRoot: ActivityCompositionRoot) {
    private val layoutInflater get() = activityCompositionRoot.layoutInflater
    private val ratesRepository get() = activityCompositionRoot.ratesRepository

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)
    val viewModelFactory get() = ViewModelFactory(ratesRepository)
    val connectivityManager get() = activityCompositionRoot.connectivityManager
}