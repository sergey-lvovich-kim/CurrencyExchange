package com.mikyegresl.currencyexchange.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mikyegresl.currencyexchange.data.Rate
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RatesDao {
    @Query("SELECT * FROM rates ORDER BY name ASC")
    fun fetchRates(): Single<List<Rate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRates(rates: List<Rate>): Completable
}