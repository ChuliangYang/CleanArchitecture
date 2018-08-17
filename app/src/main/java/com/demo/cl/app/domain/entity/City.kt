package com.demo.cl.app.domain.entity

import com.demo.cl.app.domain.entity.protocol.DomainEntity

data class City(
        val uid:Int,
        val city: String,
        val growth_from_2000_to_2013: String,
        val latitude: Double,
        val longitude: Double,
        val population: Int,
        val rank: Int,
        val state: String): DomainEntity
