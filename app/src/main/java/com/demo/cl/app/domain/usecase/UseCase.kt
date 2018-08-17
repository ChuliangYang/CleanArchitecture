package com.demo.cl.app.domain.usecase

import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.entity.protocol.LiveDataEntity
import com.demo.cl.app.domain.entity.protocol.ObservableEntity
import com.demo.cl.app.domain.entity.protocol.Reactable

sealed class UseCase<T, Result> {
    abstract fun run(params:T?=null): Reactable<DataResource<Result>>
}

abstract class UseCaseLiveData<T, Result>:UseCase<T, Result>(){
    final override fun run(params:T?): Reactable<DataResource<Result>>{
        return getLiveData(params)
    }
    abstract fun getLiveData(params:T?=null):LiveDataEntity<DataResource<Result>>
}

abstract class UseCaseObservable<T, Result>:UseCase<T, Result>(){
    final override fun run(params:T?): Reactable<DataResource<Result>>{
        return getObservable(params)
    }
    abstract fun getObservable(params:T?=null):ObservableEntity<DataResource<Result>>
}

