package com.example.simpleroomlist.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomlist.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        myViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        val mAdapter = MainRVAdapter(mutableListOf())
        binding.mainRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        myViewModel.catFactList.observe(this) {
            mAdapter.dataset = it
            mAdapter.notifyDataSetChanged()
        }
    }

    fun addButtonPressed(view: View) {
        myViewModel.getFact()
    }


    fun clearButton(view: View) {
        myViewModel.deleteAllFacts()
    }
}