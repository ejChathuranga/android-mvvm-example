package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository

class TemperHomeViewModelFactory constructor(private val repository: JobRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TemperHomeViewModel::class.java)){
            TemperHomeViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}