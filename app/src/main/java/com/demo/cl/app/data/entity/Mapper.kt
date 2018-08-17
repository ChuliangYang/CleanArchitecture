package com.demo.cl.app.data.entity

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.utils.LiveDataAdapter
import com.demo.cl.app.domain.entity.protocol.Reactable

object Mapper {
    fun <T> transformLiveData(reactable: Reactable<*>):LiveData<T>?{
        when(reactable){
            is LiveDataAdapter->{
                return reactable.original as LiveData<T>
            }
        }
        return null
    }
}