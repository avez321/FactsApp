package com.example.factsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.factsapp.di.factory.ViewModelFactory
import com.example.factsapp.di.ViewModelKey
import com.example.factsapp.di.scope.FragmentScoped
import com.example.factsapp.ui.facts.FactsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @FragmentScoped
    @ViewModelKey(FactsViewModel::class)
    abstract fun bindFactsViewModel(viewModel: FactsViewModel): ViewModel
}