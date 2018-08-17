package com.demo.cl.app.data.api

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.remote.base.NetworkResponse
import retrofit2.http.GET

interface GistService {
    @GET("c1f004cb7f447ee5ccd6433bcb56d5af/raw/df3a570c9a0976e43b799be96da59186fc918ea7/CityList.json")
    fun getCityListLive(): LiveData<NetworkResponse<List<CityEntity>>>
}