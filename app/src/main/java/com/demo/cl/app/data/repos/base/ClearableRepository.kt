package com.demo.cl.app.data.repos.base

import android.util.Log
import com.demo.cl.app.presentation.event.ViewModelClearedEvent
import com.demo.cl.app.presentation.viewmodel.base.BaseViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.reflect.KClass

abstract class ClearableRepository:BaseRepository() {
    init{
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    fun onViewModelClear(event:ViewModelClearedEvent){
        if(autoClearWith().contains(event.viewModel)){
            clear()
            EventBus.getDefault().unregister(this)
        }
    }

    abstract fun autoClearWith():List<KClass<out BaseViewModel>>

    abstract fun clear()
}