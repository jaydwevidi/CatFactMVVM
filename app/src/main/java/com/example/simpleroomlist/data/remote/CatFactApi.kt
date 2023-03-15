package com.example.simpleroomlist.data.remote

import retrofit2.http.GET

interface CatFactApi {

    @GET("/fact")
    suspend fun getFact() : CatFactResponse
}