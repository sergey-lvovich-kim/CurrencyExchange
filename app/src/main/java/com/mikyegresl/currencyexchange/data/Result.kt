package com.mikyegresl.currencyexchange.data

import com.google.gson.annotations.SerializedName

sealed class Result {
    data class Success(val rates: List<Rate>): Result()
    data class Failure(val code: String, val message: String): Result()

    enum class RateError(
        val code: String,
        val message: String
    ) {
        NOT_FOUND("404", "The requested resource does not exist."),
        NO_API_KEY("101", "No API Key was specified or an invalid API Key was specified."),
        NO_SUCH_ENDPOINT("103", "The requested API endpoint does not exist."),
        EXCEEDED_MAX_AMOUNT("104", "The maximum allowed API amount of monthly API requests has been reached."),
        NOT_SUPPORTED("105", "The current subscription plan does not support this API endpoint."),
        NO_RESULT("106", "The current request did not return any results."),
        ACCOUNT_INACTIVE("102", "The account this API request is coming from is inactive."),
        INVALID_BASE("201", "An invalid base currency has been entered."),
        INVALID_SYMBOLS("202", "One or more invalid symbols have been specified."),
        DATE_NOT_SPECIFIED("301", "No date has been specified."),
        INVALID_DATE("302", "An invalid date has been specified."),
        INVALID_AMOUNT("403", "No or an invalid amount has been specified."),
        INVALID_OR_NO_TIMEFRAME("501", "No or an invalid timeframe has been specified."),
        INVALID_START_DATE("502", "No or an invalid \"start_date\" has been specified."),
        INVALID_END_DATE("503", "No or an invalid \"end_date\" has been specified."),
        INVALID_TIMEFRAME("504", "An invalid timeframe has been specified."),
        TIMEFRAME_TOO_LONG("505", "The specified timeframe is too long, exceeding 365 days.")
    }
}