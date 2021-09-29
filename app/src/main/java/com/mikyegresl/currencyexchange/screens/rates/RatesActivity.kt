package com.mikyegresl.currencyexchange.screens.rates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikyegresl.currencyexchange.R
import com.mikyegresl.currencyexchange.screens.common.BaseActivity
import com.mikyegresl.currencyexchange.screens.convert_rates.ConvertRatesFragment

class RatesActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_rates)

        val convertRatesFragment = ConvertRatesFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, convertRatesFragment)
            .commit()
    }
}