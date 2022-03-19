package com.github.ejchathuranga.kotlin_mvvm_android.network

import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService

class JobRepository(private val retroService: RetroService?) {
    fun searchJobs() = retroService?.getJobs()
}