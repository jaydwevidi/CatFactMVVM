package com.example.simpleroomlist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleroomlist.data.remote.FetchNameHelper
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

    fun getFact(){

        viewModelScope.launch {

            val x = instance.instance.getFact()
            val temp = numList.value
            temp?.add(x.fact)
            numList.postValue(temp)
            Log.d("TAG", "getFact: $x")
        }
    }
}