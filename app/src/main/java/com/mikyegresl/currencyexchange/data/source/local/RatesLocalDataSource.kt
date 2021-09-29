package com.mikyegresl.currencyexchange.data.source.local

import androidx.lifecycle.LiveData
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import com.mikyegresl.currencyexchange.data.source.RatesDataSource
import io.reactivex.Completable
import io.reactivex.Single

class RatesLocalDataSource(
    private val ratesDao: RatesDao
): RatesDataSource {
    override fun fetchRates(): Single<Result> {
        return ratesDao.fetchRates().map { response ->
            if (response.isNotEmpty()) {
                return@map Result.Success(response)
            }
            return@map Result.Failure("201", "Empty data set in cache.")
        }
    }

    fun insertRates(rates: List<Rate>): Completable {
        return ratesDao.insertRates(rates)
    }
}