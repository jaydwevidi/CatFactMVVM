package com.example.simpleroomlist.data.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cat_fact_table")
data class CatFactResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val fact: String,
    val length: Int
){
    override fun toString(): String {
        return "Primary Key ID : $id\n" +
                "Fact : " +
                "$fact\n" +
                "Fact Character Length : $length"
    }
}