package com.example.wednesdaysolutioninternshiptask

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wednesdaysolutioninternshiptask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.recyclerListDataObserver().observe(this@MainActivity, {
            if(!it.isNullOrEmpty()){
                binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2,RecyclerView.VERTICAL, false)
                viewModel.setAdapterData(it)
            }else {
                Log.d("TAG", "onCreate: it is null or empty")
            }
        })
        Toast.makeText(this, "Search Artist Name", Toast.LENGTH_SHORT).show()
    }

}