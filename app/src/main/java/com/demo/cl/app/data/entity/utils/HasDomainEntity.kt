package com.demo.cl.app.data.entity.utils

import com.demo.cl.app.domain.entity.protocol.DomainEntity

interface HasDomainEntity<T: DomainEntity> {
    fun toDomainEntity():T
}