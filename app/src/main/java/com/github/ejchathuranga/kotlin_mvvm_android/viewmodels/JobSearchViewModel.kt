package com.github.ejchathuranga.kotlin_mvvm_android.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobSearchViewModel constructor(private val repository: JobRepository) : ViewModel() {
    private val TAG = "JobSearchViewModel"
    private var searchResultLiveData: MutableLiveData<SearchResult> = MutableLiveData()
    private var errorMsg: MutableLiveData<String> = MutableLiveData();

    fun getSearchResultLiveData(): MutableLiveData<SearchResult> {
        return this.searchResultLiveData;
    }

    fun getErrorMsg(): MutableLiveData<String> {
        return this.errorMsg;
    }

    fun searchJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchJobs()
            Log.d(TAG, "searchJobs: started")
            response?.enqueue(object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    if (response.code() == 200) {
                        Log.d(TAG, "onResponse: " + Gson().toJson(response.body()))
                        val result: SearchResult? = response.body();
                        if (result?.aggregations!!.count > 0) {
                            searchResultLiveData.postValue(response.body())
                        } else {
                            errorMsg.postValue("No Data for selected date")
                        }
                    } else {
                        errorMsg.postValue("Server Error")
                    }
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    errorMsg.postValue(t.message)
                }

            })
        }
    }
}
