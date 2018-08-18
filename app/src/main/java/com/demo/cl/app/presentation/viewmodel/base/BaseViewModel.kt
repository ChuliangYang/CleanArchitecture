package com.demo.cl.app.presentation.viewmodel.base

import android.arch.lifecycle.ViewModel
import com.demo.cl.app.presentation.event.ViewModelClearedEvent
import org.greenrobot.eventbus.EventBus

open class BaseViewModel:ViewModel(){
    var cache:MutableMap<String?,Any?>? = mutableMapOf()

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().post(ViewModelClearedEvent(this::class))
        cache?.clear()
        cache=null
    }
}

inline fun getMethodName():String{
    return Throwable().stackTrace[0].methodName
}

inline fun <T> BaseViewModel.reuseWhenAlive(additionKey:String?=null, dataProducer:()->T):T{
    val key="${getMethodName()}${additionKey?.let{
        ":$it"
    }}"
    return if(cache?.get(key) !=null){
        cache!![key] as T
    }else{
        dataProducer().apply {
            cache?.set(key, this)
        }
    }
}
