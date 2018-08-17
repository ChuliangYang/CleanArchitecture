package com.demo.cl.app.presentation.di

import com.demo.cl.app.presentation.di.base.PerFragment
import com.demo.cl.app.presentation.ui.main.CityListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class CityListFragmentSubComponentModule{
    @PerFragment
    @ContributesAndroidInjector(modules = [CityListFragmentModule::class])
    abstract fun cityListFragmentInjector(): CityListFragment
}

@Module
abstract class CityListFragmentModule{

}