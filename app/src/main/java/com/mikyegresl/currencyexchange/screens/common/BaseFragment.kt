package com.mikyegresl.currencyexchange.screens.common

import androidx.fragment.app.Fragment
import com.mikyegresl.currencyexchange.common.PresentationCompositionRoot

open class BaseFragment(): Fragment() {
    protected val compositionRoot by lazy {
        PresentationCompositionRoot(
            (requireActivity() as BaseActivity).activityCompositionRoot
        )
    }
}