package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobsearchViewModelFactory @Inject constructor(private val repository: JobRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(JobSearchViewModel::class.java)){
            JobSearchViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}