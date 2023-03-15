package com.example.simpleroomlist.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_fact_table")
data class CatFactResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fact: String,
    val length: Int
)