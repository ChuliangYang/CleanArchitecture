package com.demo.cl.app.data.transform.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.annotation.WorkerThread
import com.demo.cl.app.domain.entity.protocol.DataResource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object ReactUtil {
    
    @WorkerThread
    fun <T> toLiveDataResource(dataProducer: () -> T): LiveData<DataResource<T>> {
        val temp = MutableLiveData<DataResource<T>>()
        Single.create<DataResource<T>> {
            it.onSuccess(DataResource.success(dataProducer()))
        }.subscribeOn(Schedulers.computation()).subscribe { t1, t2 ->
            t1?.let {
                temp?.postValue(it)
            }
        }
        return temp
    }

    fun <T> toLiveDataResource(data: T): LiveData<DataResource<T>> {
        val temp = MutableLiveData<DataResource<T>>()
        if (data == null) {
            temp.postValue(DataResource.Absent())
        } else {
            temp.postValue(DataResource.success(data))
        }
        return temp
    }
}