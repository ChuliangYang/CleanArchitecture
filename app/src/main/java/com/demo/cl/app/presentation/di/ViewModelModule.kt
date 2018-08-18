package com.demo.cl.app.presentation.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.demo.cl.app.data.local.base.room.AppDatabase
import com.demo.cl.app.data.local.base.room.daos.CityDao
import com.demo.cl.app.data.local.protocol.CityLocalSource
import com.demo.cl.app.data.local.strategy.CityLocalSourceImpl
import com.demo.cl.app.data.remote.protocol.CityRemoteSource
import com.demo.cl.app.data.remote.strategy.CityRemoteSourceImpl
import com.demo.cl.app.data.repos.CityRepositoryImpl
import com.demo.cl.app.data.transform.protocol.CityTransformer
import com.demo.cl.app.data.transform.strategy.CityTransformerImpl
import com.demo.cl.app.domain.repo.CityRepository
import com.demo.cl.app.presentation.di.base.ViewModelKey
import com.demo.cl.app.presentation.viewmodel.CityListViewModel
import com.demo.cl.app.presentation.viewmodel.base.MyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Suppress("unused")
@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(CityListViewModel::class)
    abstract fun bindCityListViewModel(cityViewModel: CityListViewModel): ViewModel

    @Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindCityRepository(cityRepository: CityRepositoryImpl): CityRepository

    @Binds
    abstract fun bindCityLocalSource(cityLocalSourceImpl: CityLocalSourceImpl): CityLocalSource
    @Binds
    abstract fun bindCityRemoteSource(cityRemoteSourceImpl: CityRemoteSourceImpl): CityRemoteSource
    @Binds
    abstract fun bindCityTransformer(cityTransformerImpl: CityTransformerImpl): CityTransformer



    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideCityDao(dataBase: AppDatabase): CityDao {
            return dataBase.cityDao()
        }
    }
}