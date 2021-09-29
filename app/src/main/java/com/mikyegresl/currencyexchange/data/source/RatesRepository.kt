package com.mikyegresl.currencyexchange.data.source

import androidx.lifecycle.LiveData
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import io.reactivex.Completable
import io.reactivex.Single

interface RatesRepository {
    fun fetchRates(isNetworkAvailable: Boolean): Single<Result>

    fun saveRates(rates: List<Rate>): Completable

    fun convert(from: Rate, to: Rate, amount: Double): Double
}