package com.demo.cl.app.presentation.viewmodel.base

import android.arch.lifecycle.ViewModel
import com.demo.cl.app.data.repos.base.BaseRepository
import com.demo.cl.app.data.repos.base.ClearableRepository

open class BaseViewModel(val repo: BaseRepository?=null):ViewModel(){
    var cache:MutableMap<String?,Any?>? = mutableMapOf()

    override fun onCleared() {
        super.onCleared()
        repo?.apply{
            if(this is ClearableRepository){
                clear()
            }
        }
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
