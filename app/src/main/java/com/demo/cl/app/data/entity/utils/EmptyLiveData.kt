package com.demo.cl.app.data.entity.utils

import android.arch.lifecycle.LiveData

/**
 * A LiveDataEntity class that has `null` value.
 */
class EmptyLiveData<T : Any?> private constructor() : LiveData<T>() {
    init {
        // use post instead of set since this can be created on any thread
        postValue(null)
    }

    companion object {
        fun <T> create(): LiveData<T> {
            return EmptyLiveData()
        }
    }
}
