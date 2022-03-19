package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import kotlinx.coroutines.*

class JobSearchViewModel constructor(private val repository: JobRepository) : ViewModel() {
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
            val response = repository.searchJobs()
            withContext(Dispatchers.Main) {
                if (response != null) {
                    val result: SearchResult? = response.body()
                    if (result?.aggregations!!.count > 0) {
                        searchResultLiveData.postValue(response.body())
                    } else {
                        errorMsg.postValue("No Data for selected date")
                    }
                } else {
                    errorMsg.postValue("Server Error")
                }
            }
        }
    }

    private fun onError(message: String) {
        this.errorMsg.postValue(message)
    }
}
