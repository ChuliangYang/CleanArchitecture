package com.demo.cl.app.data.repos

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.util.Log
import com.demo.cl.app.data.entity.CityEntity
import com.demo.cl.app.data.entity.utils.EmptyLiveData
import com.demo.cl.app.data.entity.utils.LiveDataAdapter
import com.demo.cl.app.data.local.protocol.CityLocalSource
import com.demo.cl.app.data.remote.base.NetworkResponse
import com.demo.cl.app.data.remote.base.RemoteSource
import com.demo.cl.app.data.remote.protocol.CityRemoteSource
import com.demo.cl.app.data.repos.base.RxReopsitory
import com.demo.cl.app.data.repos.base.autoDispose
import com.demo.cl.app.data.transform.base.ReactUtil
import com.demo.cl.app.data.transform.protocol.CityTransformer
import com.demo.cl.app.domain.entity.City
import com.demo.cl.app.domain.entity.protocol.DataResource
import com.demo.cl.app.domain.entity.protocol.LiveDataEntity
import com.demo.cl.app.domain.repo.CityRepository
import com.demo.cl.app.presentation.viewmodel.CityListViewModel
import com.demo.cl.app.presentation.viewmodel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class CityRepositoryImpl @Inject constructor(val localSource: CityLocalSource, val remoteSource: CityRemoteSource, val transformer: CityTransformer) : RxReopsitory(), CityRepository {
    private val title = MutableLiveData<DataResource<String>>()
    private val cities: LiveData<DataResource<List<CityEntity>>>
        get() {
            return object : RemoteSource<List<CityEntity>>() {
                override fun obtainFromLocal(): LiveData<List<CityEntity>> {
                    return localSource.getCities()
                }

                override fun needFetch(data: List<CityEntity>?): Boolean {
                    return data == null || data.isEmpty()
                }

                override fun obtainFromRemote(): LiveData<NetworkResponse<List<CityEntity>>> {
                    return remoteSource.getCities()
                }

                override fun saveRemoteResult(result: List<CityEntity>?) {
                    result?.let {
                        localSource.saveCities(it)
                    }
                }
            }.toLiveData()
        }

    private fun divideIntoTwoList(totalList: List<CityEntity>): LiveData<DataResource<Array<List<City>>>> {
        return ReactUtil.toLiveDataResource {
            transformer.divideIntoTwoList(totalList)
        }
    }

    override fun getDividedCityList(): LiveDataEntity<DataResource<Array<List<City>>>> {
        return LiveDataAdapter(Transformations.switchMap(cities) { input ->
            input.original?.let {
                divideIntoTwoList(it)
            } ?: let {
                EmptyLiveData.create<DataResource<Array<List<City>>>>()
            }
        })
    }


    override fun getTitle(): LiveDataEntity<DataResource<String>> {
        title.value = DataResource.success("test title")
        autoDispose(Observable.interval(1, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe {
            title.value = DataResource.success("test title${it}")
        })
        return LiveDataAdapter(title)
    }

    override fun autoClearWith(): List<KClass<out BaseViewModel>> {
        Log.e("CityRepositoryImpl","autoClearWith${CityListViewModel::class}")
        return arrayListOf(CityListViewModel::class)
    }
}