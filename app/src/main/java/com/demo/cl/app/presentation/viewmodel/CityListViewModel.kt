package com.demo.cl.app.presentation.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.demo.cl.app.data.invokers.InvokerFactory
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.usecase.GetDividedCityList
import com.demo.cl.app.domain.usecase.GetTitle
import com.demo.cl.app.presentation.entity.CityModel
import javax.inject.Inject

class CityListViewModel @Inject constructor(val getDividedCityList: GetDividedCityList,val getTitle: GetTitle) : ViewModel() {
    fun getTwoCityList(): LiveData<DataResource<Array<List<CityModel>>>> {
        InvokerFactory.invoke(getDividedCityList)
//        return reuseWhenAlive {
//            switchMap(repository.cities) { input ->
//                input.original?.let {
//                    repository.divideIntoTwoList(it)
//                } ?: let {
//                    EmptyLiveData.create<DataResource<Array<List<City>>>>()
//                }
//            }
//        }
    }

    fun getTitle(): LiveData<DataResource<String>> {
//        return reuseWhenAlive("test") {
//            repository.getTitle()
//        }
    }
}