package com.github.ejchathuranga.kotlin_mvvm_android.network

import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    /*
    * Since only one API endpoint, didn't add query parameters on the method.
    * */

    @GET(value = "api/v3/shifts?filter[date]=2022-03-12")
    suspend fun getJobs(): SearchResult
}