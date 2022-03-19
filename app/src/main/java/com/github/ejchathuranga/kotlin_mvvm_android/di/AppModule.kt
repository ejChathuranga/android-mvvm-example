package com.github.ejchathuranga.kotlin_mvvm_android.di

import com.github.ejchathuranga.kotlin_mvvm_android.network.JobRepository
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroInstance
import com.github.ejchathuranga.kotlin_mvvm_android.network.RetroService
import com.github.ejchathuranga.kotlin_mvvm_android.viewmodels.JobsearchViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getRetroService(): RetroService{
        return RetroInstance.getRetroInstance()
    }

    @Singleton
    @Provides
    fun getJobRepository(): JobRepository {
        return JobRepository(getRetroService())
    }

    @Singleton
    @Provides
    fun getJobsearchViewModelFactory(): JobsearchViewModelFactory {
        return JobsearchViewModelFactory(getJobRepository())
    }
}
