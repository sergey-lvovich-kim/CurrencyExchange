package com.mikyegresl.currencyexchange.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class Rate(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "rate") val rate: Double
) {
    override fun toString(): String {
        return name
    }
}