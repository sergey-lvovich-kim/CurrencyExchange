package com.mikyegresl.currencyexchange.screens.convert_rates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mikyegresl.currencyexchange.R
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.screens.common.BaseViewMvc
import com.mikyegresl.currencyexchange.screens.rates.RatesAdapter

class ConvertRatesViewMvc(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?
): BaseViewMvc<ConvertRatesViewMvc.Listener>(layoutInflater, viewGroup, R.layout.layout_convert_rates) {

    interface Listener {
        fun onRefreshClicked()
        fun onFromCurrencyClicked(rate: Rate)
        fun onToCurrencyClicked(rate: Rate)
        fun onConvertClicked(inputAmount: String)
    }

    private val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)
    private val fromCurrencySpinner: Spinner = findViewById(R.id.from_currency_spinner)
    private val toCurrencySpinner: Spinner = findViewById(R.id.to_currency_spinner)
    private val inputAmountEditText: EditText = findViewById(R.id.input_amount_edit_text)
    private val exchangeRateTextView: TextView = findViewById(R.id.exchange_rate_text_view)
    private val convertedAmountTextView: TextView = findViewById(R.id.converted_amount_text_view)
    private val convertBtn: Button = findViewById(R.id.convert_btn)
    private val ratesAdapter: RatesAdapter<Rate>

    init {
        ratesAdapter = RatesAdapter(
            context,
            R.layout.rate_item,
            R.id.rate_name_text_view)
        swipeRefreshLayout.setOnRefreshListener {
            for (listener in listeners) {
                listener.onRefreshClicked()
            }
        }
        convertBtn.setOnClickListener {
            for (listener in listeners) {
                listener.onConvertClicked(inputAmountEditText.text.toString().trim())
            }
        }
        fromCurrencySpinner.adapter = ratesAdapter
        toCurrencySpinner.adapter = ratesAdapter
        exchangeRateTextView.text = context.getString(R.string.exchange_rate, 0.00f)
        convertedAmountTextView.text = context.getString(R.string.converted_amount, "")

        fromCurrencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent?.getItemAtPosition(position) is Rate) {
                    for (listener in listeners) {
                        listener.onFromCurrencyClicked(fromCurrencySpinner.adapter.getItem(position) as Rate)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }

        }
        toCurrencySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                for (listener in listeners) {
                    listener.onToCurrencyClicked(toCurrencySpinner.adapter.getItem(position) as Rate)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //do nothing
            }
        }
    }

    fun showProgressIndication() {
        swipeRefreshLayout.isRefreshing = true
    }

    fun hideProgressIndication() {
        if (swipeRefreshLayout.isRefreshing) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun bindRates(rates: List<Rate>) {
        ratesAdapter.bindRates(rates)
    }

    fun showExchangeRate(exchangeRate: Double) {
        exchangeRateTextView.text = context.getString(R.string.exchange_rate, exchangeRate)
    }

    fun showConvertedAmount(result: String) {
        convertedAmountTextView.text = context.getString(R.string.converted_amount, result)
    }

    fun displayErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}