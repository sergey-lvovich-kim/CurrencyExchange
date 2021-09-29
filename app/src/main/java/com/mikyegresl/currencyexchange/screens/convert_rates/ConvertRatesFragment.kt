package com.mikyegresl.currencyexchange.screens.convert_rates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mikyegresl.currencyexchange.R
import com.mikyegresl.currencyexchange.data.Rate
import com.mikyegresl.currencyexchange.data.Result
import com.mikyegresl.currencyexchange.screens.common.BaseFragment
import com.mikyegresl.currencyexchange.screens.rates.RatesViewModel
import com.mikyegresl.currencyexchange.util.ConnectivityManager

class ConvertRatesFragment: BaseFragment(), ConvertRatesViewMvc.Listener {
    private lateinit var viewMvc: ConvertRatesViewMvc
    private lateinit var viewModel: RatesViewModel
    private lateinit var connectivityManager: ConnectivityManager

    private var dataLoaded: Boolean = false

    companion object {
        @JvmStatic
        fun newInstance() = ConvertRatesFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = compositionRoot.viewModelFactory.createRatesViewModel()
        connectivityManager = compositionRoot.connectivityManager
    }

    override fun onDestroy() {
        super.onDestroy()

        viewModel.clearTasks()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewMvc = compositionRoot.viewMvcFactory.newConvertRatesViewMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()

        if (!dataLoaded) {
            loadRates()
        }
        viewMvc.bindListener(this)
    }

    override fun onStop() {
        super.onStop()

        viewMvc.removeListener(this)
    }


    fun loadRates() {
        viewMvc.showProgressIndication()

        viewModel.loadRates(connectivityManager.isOnline())
            .subscribe(
                {
                    viewMvc.hideProgressIndication()
                    dataLoaded = true
                    if (it is Result.Success) {
                        viewModel.saveRates(it.rates)
                        viewMvc.bindRates(it.rates)
                    }
                    if (it is Result.Failure) {
                        viewMvc.displayErrorMessage("${it.code} ${it.message}")
                    }
                },
                {
                    viewMvc.displayErrorMessage(it.toString())
                    viewMvc.hideProgressIndication()
                })
            .let {
                viewModel.addTask(it)
            }
    }

    override fun onRefreshClicked() {
        loadRates()
    }


    override fun onFromCurrencyClicked(rate: Rate) {
        viewModel.fromCurrency = rate
//        viewModel.cacheFromCurrency(rate)
    }

    override fun onToCurrencyClicked(rate: Rate) {
        viewModel.toCurrency = rate
//        viewModel.cacheToCurrency(rate)
    }

    override fun onConvertClicked(inputAmount: String) {
        val convertedAmount = viewModel.convertAmount(inputAmount)

        viewMvc.showExchangeRate(viewModel.exchangeRate)
        viewMvc.showConvertedAmount(convertedAmount)
    }
}