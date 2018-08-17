package com.demo.cl.app.data.local.protocol
import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.entity.utils.LiveDataAdapter

interface CityLocalSource{
    fun getCities(): LiveData<List<CityEntity>>
    fun saveCities(cities: List<CityEntity>)
}