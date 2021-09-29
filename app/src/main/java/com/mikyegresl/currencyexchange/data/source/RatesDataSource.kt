package com.mikyegresl.currencyexchange.data.source

import androidx.lifecycle.LiveData
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import io.reactivex.Completable
import io.reactivex.Single

interface RatesDataSource {
    fun fetchRates(): Single<Result>
}