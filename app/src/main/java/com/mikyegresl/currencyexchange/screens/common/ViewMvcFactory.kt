package com.mikyegresl.currencyexchange.screens.common

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mikyegresl.currencyexchange.screens.convert_rates.ConvertRatesViewMvc

class ViewMvcFactory(private val layoutInflater: LayoutInflater) {
    fun newConvertRatesViewMvc(parent: ViewGroup?) = ConvertRatesViewMvc(layoutInflater, parent)
}