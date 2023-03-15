package com.example.simpleroomlist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.simpleroomlist.data.remote.CatFactResponse


@Database(entities = [CatFactResponse::class], version = 1, exportSchema = true)
abstract class PersonDatabase : RoomDatabase() {

    abstract fun catFactDao(): CatFactDao

    companion object {
        private var instance: PersonDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): PersonDatabase {
            if (instance == null) instance = Room.databaseBuilder(
                ctx, PersonDatabase::class.java, "person-database"
            ).fallbackToDestructiveMigration().build()
            return instance!!

        }
    }

}

