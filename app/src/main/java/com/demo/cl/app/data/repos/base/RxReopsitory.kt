package com.demo.cl.app.data.repos.base

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxReopsitory:  ClearableRepository() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun clear() {
        clearDisposables()
    }
}

inline fun RxReopsitory.autoDispose(disposable: Disposable){
    compositeDisposable.add(disposable)
}