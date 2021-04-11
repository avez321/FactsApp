package com.example.factsapp.di.module

import com.example.factsapp.network.FactsApi
import com.example.factsapp.model.FactsClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideFactsApi(): FactsApi = FactsClient.makeFactsApi()
}