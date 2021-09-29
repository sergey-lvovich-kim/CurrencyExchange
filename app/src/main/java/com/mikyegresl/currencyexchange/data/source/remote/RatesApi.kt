package com.mikyegresl.currencyexchange.data.source.remote

import com.mikyegresl.currencyexchange.Constants
import com.mikyegresl.currencyexchange.data.RateResponseSchema
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RatesApi {
    @GET("latest?access_key=${Constants.EXCHANGE_RATES_API_KEY}&symbols=${Constants.EXCHANGE_RATES_DEFAULT_SYMBOLS}")
    fun fetchRates(): Single<Response<RateResponseSchema>>
}