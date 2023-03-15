package com.example.simpleroomlist.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatFactResponse(
    @PrimaryKey
    val id: Int,
    val fact: String,
    val length: Int
)