package com.demo.cl.app.presentation.di

import com.demo.cl.app.presentation.ui.main.MainActivityMVVM
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class MainActivitySubComponentModule{
    @ContributesAndroidInjector(modules = [CityListFragmentSubComponentModule::class])
    abstract fun mainActivityInjector(): MainActivityMVVM
}