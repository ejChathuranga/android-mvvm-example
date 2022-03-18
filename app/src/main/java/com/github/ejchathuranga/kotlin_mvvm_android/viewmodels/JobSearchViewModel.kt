package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JobSearchViewModel constructor(private val repository: JobRepository) : ViewModel() {
    private var searchResultLiveData: MutableLiveData<SearchResult> = MutableLiveData()

    fun getSearchResultLiveData(): MutableLiveData<SearchResult> {
        return this.searchResultLiveData;
    }

    fun searchJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchJobs()
            searchResultLiveData.postValue(response)
        }
    }
}
