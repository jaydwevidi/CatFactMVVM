package com.example.simpleroomlist.di

import android.app.Application
import com.example.simpleroomlist.data.local.CatFactDao
import com.example.simpleroomlist.data.local.PersonDatabase
import com.example.simpleroomlist.data.remote.CatFactApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

class AppModule {

    @Provides
    @Singleton
    fun provideCatFactAPI() : CatFactApi {
        val baseURL = "https://catfact.ninja"
        val builder =  Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(CatFactApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCatDao(appContext : Application) : CatFactDao {
        return PersonDatabase.getInstance(appContext).catFactDao()
    }
}