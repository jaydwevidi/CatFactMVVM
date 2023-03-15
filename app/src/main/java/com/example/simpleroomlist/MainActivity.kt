package com.example.simpleroomlist

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.simpleroomlist.data.local.PersonDatabase
import com.example.simpleroomlist.data.remote.CatFactResponse
import com.example.simpleroomlist.data.remote.FetchNameHelper
import com.example.simpleroomlist.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        myViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        myViewModel.get_facts_for_first_time(applicationContext)
        setupRecyclerView()


    }

    private fun setupRecyclerView(){
        val mAdapter = MainRVAdapter(mutableListOf())
        binding.mainRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        myViewModel.numList.observe(this){
            mAdapter.dataset = it
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    fun addButtonPressed(view : View)
    {
        myViewModel.getFact(application)
    }


    fun clearButton(view: View){
        myViewModel.numList.value = mutableListOf<String>()
    }
}