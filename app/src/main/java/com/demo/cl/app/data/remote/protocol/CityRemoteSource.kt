package com.demo.cl.app.data.remote.protocol

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.remote.base.NetworkResponse

interface CityRemoteSource {
    fun getCities(): LiveData<NetworkResponse<List<CityEntity>>>
}