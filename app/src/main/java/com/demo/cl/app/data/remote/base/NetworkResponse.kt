package com.demo.cl.app.data.remote.base

import retrofit2.Response

open class NetworkResponse<T> {
    companion object {
        fun <T> Success(result:T?): ResponseSuccess<T> {
            return ResponseSuccess(result)
        }

        fun <T> Failed(errorCode:Int?, errorMessage:String?=null): ResponseFailed<T> {
            return ResponseFailed(errorCode,errorMessage)
        }

        fun <T> create(response: Response<T>): NetworkResponse<T> {
             return if(response.isSuccessful) {
                ResponseSuccess(response.body())
            }else {
                var msg:String?=response.message()
                if(msg.isNullOrEmpty()){
                    msg = response.errorBody().toString()
                }
                ResponseFailed(response.code(),msg?:"Unknown Error")
            }
        }

        fun <T> create(throwable:Throwable): ResponseFailed<T> {
            return ResponseFailed(null,throwable.message?:"Unknown Error")
        }
    }
}

data class ResponseSuccess<T>(val result:T?): NetworkResponse<T>()

data class ResponseFailed<T>(val errorCode:Int?, val errorMessage:String?): NetworkResponse<T>()