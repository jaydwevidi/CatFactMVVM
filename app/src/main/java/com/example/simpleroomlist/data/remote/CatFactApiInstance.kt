package com.example.simpleroomlist.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FetchNameHelper{

    val baseURL = "https://catfact.ninja"

    fun getInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    val instance = getInstance().create(CatFactApi::class.java)

}
