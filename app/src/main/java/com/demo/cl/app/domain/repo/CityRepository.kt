package com.demo.cl.app.domain.repo

import com.demo.cl.app.domain.entity.City
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.entity.protocol.LiveDataEntity

interface CityRepository {
    fun getDividedCityList(): LiveDataEntity<DataResource<Array<List<City>>>>
    fun getTitle(): LiveDataEntity<DataResource<String>>
}