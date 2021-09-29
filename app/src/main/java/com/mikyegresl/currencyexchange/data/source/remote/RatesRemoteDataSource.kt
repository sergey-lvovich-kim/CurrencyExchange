package com.mikyegresl.currencyexchange.data.source.remote

import androidx.lifecycle.LiveData
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import com.mikyegresl.currencyexchange.data.source.RatesDataSource
import io.reactivex.Single

class RatesRemoteDataSource(
    private val ratesApi: RatesApi
): RatesDataSource {
    override fun fetchRates(): Single<Result> {
        return ratesApi.fetchRates().map { response ->
            val responseSchema = response.body()

            if (response.isSuccessful && responseSchema != null) {
                if (responseSchema.success) {
                    val rateList = mutableListOf<Rate>()
                    response.body()!!.rates.forEach {
                        rateList.add(Rate(it.key, it.value))
                    }
                    return@map Result.Success(rateList)
                }
                return@map Result.Failure(responseSchema.errorCode, responseSchema.errorMessage)
            }
            return@map Result.Failure(response.code().toString(), response.message())
        }
    }
}