package com.example.factsapp.di.module

import android.app.Application
import com.example.factsapp.shared_preference.SharedPreferenceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefModule {
    @Singleton
    @Provides
    fun providePreferenceRepository(application: Application): SharedPreferenceRepository {
       return SharedPreferenceRepository(application)
    }
}