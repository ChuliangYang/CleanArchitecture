package com.demo.cl.app.data.local.base.room.daos
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.demo.cl.app.data.entity.CityEntity


@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCities(cities: List<CityEntity>)

    @Update
    fun updateCity(city: CityEntity): Int

    @Update
    fun updateCities(cities: List<CityEntity>): Int

    @Update
    fun updateCities(vararg users: CityEntity)

    @Delete
    fun deleteCity(city: CityEntity): Int

    @Delete
    fun deleteCities(cities: List<CityEntity>): Int

    @Delete
    fun deleteCities(vararg cities: CityEntity): Int

    @Query("select * from cities")
    fun getAll(): LiveData<List<CityEntity>>

    @Query("select * from cities")
    fun getCities(): LiveData<Array<CityEntity>>

    @Query("delete from cities where city =:name")
    fun removeCity(name: String)
}