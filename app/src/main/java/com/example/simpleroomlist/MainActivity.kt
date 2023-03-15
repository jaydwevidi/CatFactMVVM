package com.example.simpleroomlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleroomlist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupViewModel()
        setupRecyclerView()
    }

    private fun setupViewModel() {
        myViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        myViewModel.setupContext(applicationContext)
        myViewModel.update_livedata_from_database()
    }

    private fun setupRecyclerView() {
        val mAdapter = MainRVAdapter(mutableListOf())
        binding.mainRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        myViewModel.numList.observe(this) {
            mAdapter.dataset = it
            binding.mainRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    fun addButtonPressed(view: View) {
        myViewModel.getFact()
    }


    fun clearButton(view: View) {
        myViewModel.delete_all_facts()
    }
}