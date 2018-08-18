package com.demo.cl.app.presentation.event

import android.arch.lifecycle.ViewModel
import com.demo.cl.app.presentation.viewmodel.base.BaseViewModel
import kotlin.reflect.KClass

data class ViewModelClearedEvent(val viewModel:KClass<out BaseViewModel>)