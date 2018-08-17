package com.demo.cl.app.domain.entity.protocol

// Encapsulate original data as well as loading status
data class DataResource<out OriginalType>(val loadingStatus: DataStatus, val original:OriginalType?, val extraMessage:String?){
    companion object {
        fun <T> success(originalData:T?, extraMessage:String?=null): DataResource<T> {
            return DataResource(DataStatus.SUCCESS, originalData, extraMessage)
        }
        fun <T> failed(extraMessage:String?, alternativeData:T?=null): DataResource<T> {
            return DataResource(DataStatus.FAILED, alternativeData, extraMessage)
        }
        fun <T> loading(progress:Int?=null, originalData:T?=null): DataResource<T> {
            return DataResource(DataStatus.LOADING, originalData, progress?.toString())
        }
        fun <T> Absent(): DataResource<T> {
            return DataResource(DataStatus.NULL, null, null)
        }
    }
}

enum class DataStatus{
    SUCCESS,
    FAILED,
    LOADING,
    NULL
}
