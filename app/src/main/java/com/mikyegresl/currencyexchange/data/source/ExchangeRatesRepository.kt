package com.mikyegresl.currencyexchange.data.source

import androidx.lifecycle.LiveData
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import com.mikyegresl.currencyexchange.data.source.local.RatesLocalDataSource
import com.mikyegresl.currencyexchange.data.source.remote.RatesRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ExchangeRatesRepository(
    private val remoteDataSource: RatesRemoteDataSource,
    private val localDataSource: RatesLocalDataSource
): RatesRepository {
    override fun fetchRates(isNetworkAvailable: Boolean): Single<Result> {
        var localResult = localDataSource.fetchRates()

        if (isNetworkAvailable) {
            return remoteDataSource.fetchRates()
                .doOnError {
                    localResult = localDataSource.fetchRates()
                }
        }
        return localDataSource.fetchRates()
    }

    override fun saveRates(rates: List<Rate>): Completable {
        return localDataSource.insertRates(rates)
    }

    override fun convert(from: Rate, to: Rate, amount: Double): Double {
        if (amount <= 0) {
            return 0.0
        }
        return amount * to.rate / from.rate
    }
}