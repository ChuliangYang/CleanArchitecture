package com.demo.cl.app.data.remote.strategy

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.api.GistService
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.remote.base.NetworkResponse
import com.demo.cl.app.data.remote.protocol.CityRemoteSource
import javax.inject.Inject

class CityRemoteSourceImpl @Inject constructor(val gistService: GistService): CityRemoteSource {
    override fun getCities(): LiveData<NetworkResponse<List<CityEntity>>> {
        return gistService.getCityListLive()
    }
}