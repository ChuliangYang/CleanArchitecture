package com.demo.cl.app.data.transform.protocol

import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.domain.entity.City

interface CityTransformer {
    fun divideIntoTwoList(totalList: List<CityEntity>): Array<List<City>>
}