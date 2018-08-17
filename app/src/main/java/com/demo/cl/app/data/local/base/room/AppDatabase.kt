package com.demo.cl.app.data.local.base.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.local.base.room.daos.CityDao

@Database(entities= arrayOf(CityEntity::class),version=1)
abstract class AppDatabase: RoomDatabase() {
   abstract fun cityDao(): CityDao
}