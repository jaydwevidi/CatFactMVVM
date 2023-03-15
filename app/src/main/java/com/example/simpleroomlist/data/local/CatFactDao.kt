package com.example.simpleroomlist.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpleroomlist.data.remote.CatFactResponse


@Dao
interface CatFactDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(facts: CatFactResponse)

    @Query("SELECT * FROM catfactresponse")
    fun getAll() : List<CatFactResponse>
}