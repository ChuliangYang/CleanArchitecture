package com.demo.cl.app.data.entity.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.entity.protocol.Reactable
import com.demo.cl.app.domain.usecase.UseCase
import com.demo.cl.app.domain.usecase.UseCaseLiveData
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

sealed class ReactInvoker<output> {
    var useCase: (() -> Reactable<*>)?=null

    fun <input> config(usecase: UseCase<input, *>, params: input? = null): ReactInvoker<output> {
        useCase = {
            usecase.run(params)
        }
        return this
    }

    abstract fun execute(): output?


}
class LiveDataInvoker : ReactInvoker<LiveData<*>>() {
    override fun execute(): LiveData<*>? {
        val temp = MediatorLiveData<Any?>()
        var result: Reactable<*>? = null
        Completable.create {
            result = useCase?.invoke()
            it.onComplete()
        }.subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe {
                    if (result is LiveDataAdapter) {
                        temp.addSource((result as LiveDataAdapter<*>).original) {
                            it?.let {
                                temp.value = it
                            }
                        }
                    }
                }
        return temp
    }

    fun <input,output> executeUseCase(usecase: UseCaseLiveData<input, output>, params: input? = null):LiveData<DataResource<output>> {
        return config(usecase, params).execute() as LiveData<DataResource<output>>
    }
}

