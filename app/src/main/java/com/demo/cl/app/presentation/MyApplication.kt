package com.demo.cl.app.presentation

import android.app.Activity
import android.app.Application
import com.demo.cl.app.presentation.di.base.DaggerAutoInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MyApplication:Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        DaggerAutoInjector.inject(this)
    }
}