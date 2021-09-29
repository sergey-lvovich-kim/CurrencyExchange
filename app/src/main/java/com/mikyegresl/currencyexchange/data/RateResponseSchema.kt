package com.mikyegresl.currencyexchange.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RateResponseSchema(
    val success: Boolean,
    @Expose @SerializedName("code") val errorCode: String,
    @Expose @SerializedName("message") val errorMessage: String,
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: HashMap<String, Double>
)