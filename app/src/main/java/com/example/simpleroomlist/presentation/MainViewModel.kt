package com.example.simpleroomlist.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleroomlist.data.local.CatFactDao
import com.example.simpleroomlist.data.local.PersonDatabase
import com.example.simpleroomlist.data.remote.CatFactApi
import com.example.simpleroomlist.data.remote.CatFactResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val catFactApi: CatFactApi , private  val appContext: Application) : ViewModel() {
    val catFactList = MutableLiveData<MutableList<CatFactResponse>>()
    private lateinit var catDao: CatFactDao

    init {
        catFactList.value = mutableListOf()
        setupDao()
    }

    private fun setupDao(){
        val db = PersonDatabase.getInstance(appContext)
        catDao = db.catFactDao()
    }


    fun getFact(){
        viewModelScope.launch {

            val newCatFact = catFactApi.getFact()
            addFactToDb(newCatFact)
            updateLivedataFromDatabase()
        }
    }

    private fun addFactToDb(fact : CatFactResponse){
        viewModelScope.launch(Dispatchers.IO) {
            catDao.insert(fact)
            updateLivedataFromDatabase()
        }
    }

    fun updateLivedataFromDatabase(){
        viewModelScope.launch(Dispatchers.IO) {
            val factDBList = catDao.getAll()
            catFactList.postValue(factDBList.toMutableList())
        }
    }


    fun deleteAllFacts(){
        viewModelScope.launch(Dispatchers.IO) {
            catDao.deleteAll()
            updateLivedataFromDatabase()
        }
    }
}