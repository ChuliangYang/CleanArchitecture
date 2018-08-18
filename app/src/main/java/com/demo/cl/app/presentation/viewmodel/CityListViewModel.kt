package com.demo.cl.app.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.demo.cl.app.data.entity.utils.LiveDataInvoker
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.usecase.GetDividedCityList
import com.demo.cl.app.domain.usecase.GetTitle
import com.demo.cl.app.presentation.entity.CityModel
import com.demo.cl.app.presentation.viewmodel.base.BaseViewModel
import com.demo.cl.app.presentation.viewmodel.base.reuseWhenAlive
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CityListViewModel @Inject constructor(val getDividedCityList: GetDividedCityList, val getTitle: GetTitle) : BaseViewModel() {
    fun getTwoCityList(): LiveData<DataResource<Array<List<CityModel>>>> {
        return reuseWhenAlive {
            Transformations.map(LiveDataInvoker().executeUseCase(getDividedCityList)) {
                val result = mutableListOf<List<CityModel>>()
                it.original?.forEach {
                    result.add(it.map {
                        CityModel(it)
                    })
                }
                DataResource(it.loadingStatus, result.toTypedArray(),it.extraMessage)
            }
        }
    }

    fun getTitle(): LiveData<DataResource<String>> {
        return reuseWhenAlive("addition"){
            LiveDataInvoker().executeUseCase(getTitle)
        }
    }
}