package com.github.ejchathuranga.kotlin_mvvm_android.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object {
        private const val BASE_URL = "https://temper.works/"

        private var retroService: RetroService? = null

        fun getRetroInstance(): RetroService? {

            if (retroService == null) {
                val retro = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

                retroService = retro.create(RetroService::class.java)
            }
            return retroService;
        }
    }
}