package com.github.ejchathuranga.kotlin_mvvm_android.network

import com.github.ejchathuranga.kotlin_mvvm_android.models.SearchResult
import retrofit2.Call
import retrofit2.http.GET

interface RetroService {

    /*
    * Since only one API endpoint, didn't add query parameters on the method.
    * */

    @GET(value = "api/v3/shifts?filter[date]=2022-03-19&filter[only_freelance]&remember_parameters=true&filter[categories]=g8e9r8&filter[skills]=9r8978&filter[distance][distance]=30&filter[distance][lat]=52.38028&filter[distance][lon]=4.64056&sort=earliest&filter[hourly_rate]=20")
    fun getJobs(): Call<SearchResult>
}