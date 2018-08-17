package com.demo.cl.app.presentation.di

import android.app.Application
import android.arch.persistence.room.Room
import com.demo.cl.app.data.api.GIST_HOST
import com.demo.cl.app.data.api.GistService
import com.demo.cl.app.data.api.utils.LiveDataCallAdapterFactory
import com.demo.cl.app.data.local.base.room.AppDatabase
import com.demo.cl.app.presentation.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.support.AndroidSupportInjectionModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(GIST_HOST).client(OkHttpClient.Builder().apply {
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(LiveDataCallAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun provideGistService(retrofit: Retrofit): GistService {
        return retrofit.create(GistService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "material-tab").build()
    }
}

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    MainActivitySubComponentModule::class
])
interface AppComponent{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application:Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: MyApplication)
}
