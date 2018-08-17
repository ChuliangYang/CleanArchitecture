package com.demo.cl.app.data.local.strategy

import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.entity.utils.LiveDataAdapter
import com.demo.cl.app.data.local.base.room.daos.CityDao
import com.demo.cl.app.data.local.protocol.CityLocalSource

class CityLocalSourceImpl constructor(val cityDao: CityDao): CityLocalSource {

    override fun getCities(): LiveDataAdapter<List<CityEntity>> {
        return cityDao.getAll() as LiveDataAdapter<List<CityEntity>>
    }
    override fun saveCities(cities:List<CityEntity>){
        cityDao.insertCities(cities)
    }
}