package com.github.ejchathuranga.kotlin_mvvm_android.views.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.ejchathuranga.kotlin_mvvm_android.R
import com.github.ejchathuranga.kotlin_mvvm_android.databinding.TemperActivityMainBinding
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroInstance
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService
import com.github.ejchathuranga.kotlin_mvvm_android.viewmodels.JobSearchViewModel
import com.github.ejchathuranga.kotlin_mvvm_android.viewmodels.JobsearchViewModelFactory
import com.github.ejchathuranga.kotlin_mvvm_android.views.SigninActivity
import com.github.ejchathuranga.kotlin_mvvm_android.views.SignupActiviity

class TemperHomeActivity : AppCompatActivity() {
    private val TAG = "TemperHomeActivity"

    lateinit var binding: TemperActivityMainBinding
    lateinit var viewModel: JobSearchViewModel
    lateinit var adapter: JobsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temper_activity_main)
        binding = TemperActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initRecycler()
        buttonAction()

        observe()

    }

    private fun initViewModel() {
        val retroService: RetroService = RetroInstance.getRetroInstance()!!
        viewModel =
            ViewModelProvider(this, JobsearchViewModelFactory(JobRepository(retroService))).get(
                JobSearchViewModel::class.java
            );
    }

    private fun initRecycler() {
        adapter = JobsAdapter()
        binding.recyclerview.adapter = adapter

        viewModel.searchJobs()

    }


    private fun buttonAction() {

        binding.btnLogin.setOnClickListener(View.OnClickListener {
            var newIntent = Intent(this, SigninActivity::class.java )
            startActivity(newIntent)
        })

        binding.btnSignup.setOnClickListener {
            var newIntent = Intent(this, SignupActiviity::class.java )
            startActivity(newIntent)
        }
    }



    private fun observe() {

        viewModel.getSearchResultLiveData().observe(this, Observer {
            if (it != null) {
                adapter.setDataList(it.data)
            } else {
                Toast.makeText(this, "Error on search result", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.getErrorMsg().observe(this) {
            if (it != null) {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

    }

}