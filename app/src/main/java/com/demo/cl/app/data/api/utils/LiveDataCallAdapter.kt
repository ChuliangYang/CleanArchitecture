package com.demo.cl.app.data.api.utils


import android.arch.lifecycle.LiveData
import com.demo.cl.app.data.remote.base.NetworkResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A Retrofit adapter that converts the Call into a LiveData of NetworkResponse.
 * @param <R>
</R> */
class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<NetworkResponse<R>>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): LiveData<NetworkResponse<R>> {
        return object : LiveData<NetworkResponse<R>>() {
            private var started = AtomicBoolean(false)
            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(NetworkResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, throwable: Throwable) {
                            postValue(NetworkResponse.create(throwable))
                        }
                    })
                }
            }
        }
    }
}
