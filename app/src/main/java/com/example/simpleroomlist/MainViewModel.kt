package com.example.simpleroomlist

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.simpleroomlist.data.local.PersonDatabase
import com.example.simpleroomlist.data.remote.CatFactResponse
import com.example.simpleroomlist.data.remote.FetchNameHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val numList = MutableLiveData<MutableList<String>>()
    lateinit var instance : FetchNameHelper

    init {
        numList.value = mutableListOf()
        instance = FetchNameHelper

    }

    fun addData(num : Int){
        numList
    }

    fun getFact(context: Context){
        viewModelScope.launch {

            val x = instance.instance.getFact()
            val temp = numList.value
            temp?.add(x.fact)
            numList.postValue(temp)
            add_to_db(context , x)
            Log.d("TAG", "getFact: $x")
        }
    }

    fun add_to_db(context: Context , fact : CatFactResponse){
        Log.d("TAG", "add_to_db: ")
        val db = PersonDatabase.getInstance(context)
        viewModelScope.launch(Dispatchers.IO) {
            db.catFactDao().insert(fact)
            val factDBList = db.catFactDao().getAll()
            Log.d("TAG", "add_to_db: ${factDBList}")

        }
    }
}