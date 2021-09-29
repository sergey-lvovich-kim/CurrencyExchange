package com.mikyegresl.currencyexchange.screens.common

import com.mikyegresl.currencyexchange.data.source.RatesRepository
import com.mikyegresl.currencyexchange.screens.rates.RatesViewModel

class ViewModelFactory(private val ratesRepository: RatesRepository) {
    fun createRatesViewModel(): RatesViewModel = RatesViewModel(ratesRepository)
}