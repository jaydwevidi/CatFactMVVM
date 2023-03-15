package com.example.simpleroomlist

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleroomlist.data.local.PersonDatabase
import com.example.simpleroomlist.data.remote.CatFactResponse
import com.example.simpleroomlist.data.remote.FetchNameHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val numList = MutableLiveData<MutableList<CatFactResponse>>()
    var instance : FetchNameHelper
    lateinit var context : Context

    init {
        numList.value = mutableListOf()
        instance = FetchNameHelper

    }

    fun setupContext(context : Context){
        this.context = context
    }

    fun addData(num : Int){
        numList
    }

    fun getFact(){
        viewModelScope.launch {

            val newCatFact = instance.instance.getFact()
            add_to_db(newCatFact)
            update_livedata_from_database()
        }
    }

    private fun add_to_db(fact : CatFactResponse){
        Log.d("TAG", "add_to_db: ")
        val db = PersonDatabase.getInstance(context)
        viewModelScope.launch(Dispatchers.IO) {
            db.catFactDao().insert(fact)
            val factDBList = db.catFactDao().getAll()
            Log.d("TAG", "add_to_db: ${factDBList}")
            update_livedata_from_database()
        }
    }

    fun update_livedata_from_database(){
        Log.d("TAG", "add_to_db: ")
        val db = PersonDatabase.getInstance(context)
        viewModelScope.launch(Dispatchers.IO) {
            val factDBList = db.catFactDao().getAll()
            Log.d("TAG", "add_to_db: ${factDBList}")
            numList.postValue(factDBList.toMutableList())
        }
    }


    fun delete_all_facts(){
        val db = PersonDatabase.getInstance(context)
        viewModelScope.launch(Dispatchers.IO) {
            db.catFactDao().deleteAll()
            update_livedata_from_database()
        }
    }
}