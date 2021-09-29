package com.mikyegresl.currencyexchange.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mikyegresl.currencyexchange.data.Rate

@Database(entities = [Rate::class], version = 1, exportSchema = false)
abstract class RatesDatabase(): RoomDatabase() {
    abstract fun ratesDao(): RatesDao
}