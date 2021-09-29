package com.mikyegresl.currencyexchange.screens.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes

open class BaseViewMvc<LISTENER_TYPE>(
    layoutInflater: LayoutInflater,
    viewGroup: ViewGroup?,
    @LayoutRes private val layoutResId: Int
) {
    protected val listeners = HashSet<LISTENER_TYPE>()
    protected val context: Context = layoutInflater.context
    val rootView: View = layoutInflater.inflate(layoutResId, viewGroup, false)

    fun bindListener(listener: LISTENER_TYPE) {
        listeners.add(listener)
    }

    fun removeListener(listener: LISTENER_TYPE) {
        listeners.remove(listener)
    }

    protected fun<T: View> findViewById(@IdRes resId: Int): T {
        return rootView.findViewById(resId)
    }
}