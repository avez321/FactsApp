package com.example.factsapp.di.module

import com.example.factsapp.network.FactsApi
import com.example.factsapp.repository.RepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRepositoryImp(factsApi: FactsApi): RepositoryImp = RepositoryImp(factsApi)
}