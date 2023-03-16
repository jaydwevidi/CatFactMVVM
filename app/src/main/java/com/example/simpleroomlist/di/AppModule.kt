package com.example.simpleroomlist.di

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

}