package com.mikyegresl.currencyexchange.screens.rates

import android.content.Context
import android.widget.ArrayAdapter
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

class RatesAdapter<T>(
    context: Context,
    @LayoutRes layoutRes: Int,
    @IdRes resId: Int,
    private var rateList: List<T> = mutableListOf()
): ArrayAdapter<T>(context, layoutRes, resId, rateList) {

    override fun getCount(): Int {
        return rateList.size
    }

    override fun getItem(position: Int): T? {
        return rateList[position]
    }

    fun bindRates(rates: List<T>) {
        rateList = rates
        notifyDataSetChanged()
    }
}