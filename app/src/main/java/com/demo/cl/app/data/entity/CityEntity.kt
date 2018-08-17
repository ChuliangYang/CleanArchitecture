package com.demo.cl.app.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.demo.cl.app.data.entity.utils.HasDomainEntity
import com.demo.cl.app.domain.entity.City

@Entity(tableName = "cities")
data class CityEntity(
        val city: String,
        val growth_from_2000_to_2013: String,
        val latitude: Double,
        val longitude: Double,
        val population: String,
        val rank: String,
        val state: String) : Parcelable, HasDomainEntity<City> {

    @PrimaryKey(autoGenerate = true)
    var uid:Int=0

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(city)
        parcel.writeString(growth_from_2000_to_2013)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(population)
        parcel.writeString(rank)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityEntity> {
        override fun createFromParcel(parcel: Parcel): CityEntity {
            return CityEntity(parcel)
        }

        override fun newArray(size: Int): Array<CityEntity?> {
            return arrayOfNulls(size)
        }
    }

    override fun toDomainEntity(): City {
        return City(uid,city, growth_from_2000_to_2013, latitude, longitude, population.toInt(),rank.toInt(), state)
    }

}
