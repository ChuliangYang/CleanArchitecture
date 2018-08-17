package com.demo.cl.app.presentation.entity

import com.demo.cl.app.domain.entity.City

data class CityModel(
        val uid:Int,
        val city: String,
        val population: String,
        val state: String) {
    constructor(city:City):this(city.uid,city.city,city.population.toString(),city.state)
}