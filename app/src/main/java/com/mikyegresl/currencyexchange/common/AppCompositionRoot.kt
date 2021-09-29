package com.mikyegresl.currencyexchange.common

import android.content.Context
import androidx.room.Room
import com.mikyegresl.currencyexchange.Constants
import com.mikyegresl.currencyexchange.data.source.ExchangeRatesRepository
import com.mikyegresl.currencyexchange.data.source.RatesRepository
import com.mikyegresl.currencyexchange.data.source.local.RatesDatabase
import com.mikyegresl.currencyexchange.data.source.local.RatesLocalDataSource
import com.mikyegresl.currencyexchange.data.source.remote.RatesApi
import com.mikyegresl.currencyexchange.data.source.remote.RatesRemoteDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class AppCompositionRoot(appContext: Context) {
    private val database by lazy {
        Room.databaseBuilder(
            appContext,
            RatesDatabase::class.java, DB_NAME
        ).build()
    }

    private val retrofit: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        Retrofit.Builder()
            .baseUrl(Constants.EXCHANGE_RATES_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
    }

    private val ratesApi by lazy {
        retrofit.create(RatesApi::class.java)
    }

    fun createRatesRepository(): RatesRepository {
        return ExchangeRatesRepository(
            RatesRemoteDataSource(ratesApi),
            RatesLocalDataSource(database.ratesDao())
        )
    }
}

private const val DB_NAME = "Rates.db"