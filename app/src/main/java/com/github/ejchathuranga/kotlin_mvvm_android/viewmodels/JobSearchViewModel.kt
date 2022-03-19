package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class JobSearchViewModel @Inject constructor(private val repository: JobRepository) : ViewModel() {
    private val TAG = "JobSearchViewModel"
    private var searchResultLiveData: MutableLiveData<SearchResult> = MutableLiveData()
    private var errorMsg: MutableLiveData<String> = MutableLiveData();

    private var job: Job? = null

    // use global exception handler provide by Coroutine
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getSearchResultLiveData(): MutableLiveData<SearchResult> {
        return this.searchResultLiveData;
    }

    fun getErrorMsg(): MutableLiveData<String> {
        return this.errorMsg;
    }

    fun searchJobs() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            repository.searchJobs(getToday(), searchResultLiveData, errorMsg)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getToday(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(Date());
    }

    private fun onError(message: String) {
        this.errorMsg.postValue(message)
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
