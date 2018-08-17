package com.demo.cl.app.domain.entity.protocol

sealed class Reactable<T>

abstract class LiveDataEntity<T>:Reactable<T>()

abstract class ObservableEntity<T>:Reactable<T>()
