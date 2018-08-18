package com.demo.cl.app.data.entity.utils

import android.arch.lifecycle.LiveData
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.entity.protocol.LiveDataEntity
import com.demo.cl.app.domain.entity.protocol.ObservableEntity
import io.reactivex.Observable


class LiveDataAdapter<T>(val original: LiveData<T>): LiveDataEntity<T>(){
}

class ObservableAdapter<T>(val original:Observable<T>): ObservableEntity<T>(){
}