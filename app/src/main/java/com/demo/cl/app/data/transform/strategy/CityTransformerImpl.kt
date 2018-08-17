package com.demo.cl.app.data.transform.strategy

import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.transform.protocol.CityTransformer
import com.demo.cl.app.domain.entity.City

class CityTransformerImpl constructor(): CityTransformer {
    override fun divideIntoTwoList(totalList: List<CityEntity>): Array<List<City>> {
        val listOne = mutableListOf<City>()
        val listTwo = mutableListOf<City>()

        totalList.forEach {
            if (it.rank.toInt() < 500) {
                listOne.add(it.toDomainEntity())
            } else {
                listTwo.add(it.toDomainEntity())
            }
        }
        return arrayOf(listOne,listTwo)
    }
}