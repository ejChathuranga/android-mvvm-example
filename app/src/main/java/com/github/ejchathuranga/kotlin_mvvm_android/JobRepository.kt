package com.github.ejchathuranga.kotlin_mvvm_android

import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService

class JobRepository(private val retroService: RetroService?) {
    suspend fun searchJobs() = retroService?.getJobs()
}