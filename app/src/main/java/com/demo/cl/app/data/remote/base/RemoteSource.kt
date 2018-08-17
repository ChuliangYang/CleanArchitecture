package com.demo.cl.app.data.remote.base

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.demo.cl.app.domain.entity.protocol.DataResource
import kotlinx.coroutines.experimental.async

// Use local database as SSOT, local data will sync with remote data
abstract class RemoteSource<ResultType>{
    val result = MediatorLiveData<DataResource<ResultType>>()
    init {
        result.value= DataResource.loading()
        val dataSource=obtainFromLocal()
        result.addSource(dataSource) { data ->
            result.removeSource(dataSource)
            if(needFetch(data)){
                fetchFromRemote(data)
            }else{
                result.addSource(dataSource) {
                    result.value= DataResource.success(it)
                }
            }
        }
    }

    private fun fetchFromRemote(dbResult:ResultType?) {
        val remoteSource=obtainFromRemote()
        result.addSource(remoteSource){ response ->
            result.removeSource(remoteSource)
            when(response){
                is ResponseSuccess ->{
                    onRemoteFetchSuccess()
                    async{
                        saveRemoteResult(processResponse(response))
                    }
//                    Completable.create {
//                        saveRemoteResult(processResponse(response))
//                        it.onComplete()
//                    }.subscribeOn(Schedulers.io()).subscribe()
                }
                is ResponseFailed ->{
                    onRemoteFetchFailed()
                    result.value= DataResource.failed(response.errorMessage, dbResult)
                }
            }
            result.addSource(obtainFromLocal()) {
                result.value= DataResource.success(it)
            }
        }
    }

    fun toLiveData():LiveData<DataResource<ResultType>>{
        return result
    }

    open fun onRemoteFetchSuccess(){

    }
    open fun onRemoteFetchFailed(){

    }

    open fun processResponse(response: ResponseSuccess<ResultType>):ResultType?{
        return response.result
    }

    abstract fun obtainFromRemote():LiveData<NetworkResponse<ResultType>>
    abstract fun obtainFromLocal():LiveData<ResultType>
    abstract fun saveRemoteResult(result:ResultType?)
    abstract fun needFetch(data:ResultType?):Boolean

}