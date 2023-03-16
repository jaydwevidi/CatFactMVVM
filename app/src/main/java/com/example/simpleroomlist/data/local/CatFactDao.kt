package com.example.simpleroomlist.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpleroomlist.data.remote.CatFactResponse


@Dao
interface CatFactDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(facts: CatFactResponse)

    @Query("SELECT * FROM cat_fact_table")
    suspend fun getAll() : List<CatFactResponse>

    @Query("DELETE FROM cat_fact_table")
    suspend fun deleteAll()
}