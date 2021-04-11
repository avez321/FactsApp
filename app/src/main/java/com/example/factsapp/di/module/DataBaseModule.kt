package com.example.factsapp.di.module

import android.app.Application
import com.example.factsapp.database.FactsDatabase
import com.example.factsapp.database.FactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun provideFactsRepository(application: Application): FactsRepository{
       val factsDao = FactsDatabase.getDatabase(application).factsDao()
        return FactsRepository(factsDao)
    }
}