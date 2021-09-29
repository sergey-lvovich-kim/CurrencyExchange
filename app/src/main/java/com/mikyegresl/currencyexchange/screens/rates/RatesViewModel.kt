package com.mikyegresl.currencyexchange.screens.rates

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import com.mikyegresl.currencyexchange.data.source.RatesRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RatesViewModel(
    private val ratesRepository: RatesRepository
): ViewModel() {
    private val disposableList = CompositeDisposable()

    var fromCurrency: Rate? = null
    var toCurrency: Rate? = null
    val exchangeRate: Double get() {
        if (toCurrency == null || fromCurrency == null) {
            return 0.0
        }
        return toCurrency!!.rate / fromCurrency!!.rate
    }

    fun loadRates(networkAvailable: Boolean): Single<Result> {
        return ratesRepository.fetchRates(networkAvailable)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun saveRates(rates: List<Rate>) {
        ratesRepository.saveRates(rates)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe().let {
                addTask(it)
            }
    }

    fun convertAmount(exchangeAmount: String): String {
        if (TextUtils.isEmpty(exchangeAmount)) {
            return "Empty field..."
        }
        if (!TextUtils.isDigitsOnly(exchangeAmount)) {
            return "Invalid format..."
        }
        if (exchangeAmount.length >= 10) {
            return "Amount is too long..."
        }
        if (toCurrency == null || fromCurrency == null) {
            return "Some currency is empty"
        }
        val amount = exchangeAmount.toDouble()
        return ratesRepository.convert(fromCurrency!!, toCurrency!!, amount).toString()
    }

    fun addTask(disposable: Disposable) {
        disposableList.add(disposable)
    }

    fun clearTasks() {
        disposableList.clear()
    }
}