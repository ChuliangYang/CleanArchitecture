package com.demo.cl.app.data.local.strategy

import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.entity.utils.LiveDataAdapter
import com.demo.cl.app.data.local.base.room.daos.CityDao
import com.demo.cl.app.data.local.protocol.CityLocalSource
import javax.inject.Inject

class CityLocalSourceImpl @Inject constructor(val cityDao: CityDao): CityLocalSource {

    override fun getCities(): LiveData<List<CityEntity>> {
        return cityDao.getAll()
    }
    override fun saveCities(cities:List<CityEntity>){
        cityDao.insertCities(cities)
    }
}