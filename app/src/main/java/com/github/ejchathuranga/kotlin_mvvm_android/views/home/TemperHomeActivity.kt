package com.github.ejchathuranga.kotlin_mvvm_android.views.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.ejchathuranga.kotlin_mvvm_android.R
import com.github.ejchathuranga.kotlin_mvvm_android.databinding.TemperActivityMainBinding
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroInstance
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService
import com.github.ejchathuranga.kotlin_mvvm_android.viewmodels.TemperHomeViewModel
import com.github.ejchathuranga.kotlin_mvvm_android.viewmodels.TemperHomeViewModelFactory

class TemperHomeActivity : AppCompatActivity() {
    private val TAG = "TemperHomeActivity"

    lateinit var binding : TemperActivityMainBinding
    lateinit var viewModel: TemperHomeViewModel
    lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temper_activity_main)
        binding = TemperActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecycler()

    }

    private fun initRecycler() {
        adapter = JobsAdapter()
        binding.recyclerview.adapter = adapter

        viewModel.makeApiCall()
    }

    private fun initViewModel() {
        val retroService: RetroService? = RetroInstance.getRetroInstance()
        viewModel = ViewModelProvider(this, TemperHomeViewModelFactory(JobRepository(retroService))).get(TemperHomeViewModel::class.java);

        viewModel.getSearchResultLiveData().observe(this, Observer {
            if (it!=null){
                adapter.setDataList(it.data)
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}