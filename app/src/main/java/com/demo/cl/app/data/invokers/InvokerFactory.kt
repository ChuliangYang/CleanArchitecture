package com.demo.cl.app.data.invokers

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.Mapper
import com.demo.cl.app.domain.usecase.UseCase
import com.demo.cl.app.domain.usecase.UseCaseLiveData

object InvokerFactory {
    fun <T,V> invoke(useCase: UseCase<T, V>, resultListener: ResultListener?=null, params: T?=null): Invoker {
        when (useCase) {
            is UseCaseLiveData -> {
                useCase.run(params)
//                   return  Mapper.transformLiveData(useCase.run(params))
            }
        }
    }
}

class LiveDataInvoker:Invoker

interface Invoker{
    fun <T,P> invoke(p:P):T
}

interface ResultListener {
    fun onSuccess()
    fun onFailed()
}