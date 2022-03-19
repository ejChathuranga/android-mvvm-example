package com.github.ejchathuranga.kotlin_mvvm_android.network

import androidx.lifecycle.MutableLiveData
import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JobRepository @Inject constructor(private val retroService: RetroService) {

    suspend fun searchJobs(
        date: String,
        searchResultLiveData: MutableLiveData<SearchResult>,
        errorMsg: MutableLiveData<String>
    ) {
        withContext(Dispatchers.Main) {
            val call: Call<SearchResult> = retroService.getJobs(date)
            call.enqueue(object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    if (response.code() == 200) {
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

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    errorMsg.postValue(t.message)
                }
            })
        }
    }
}