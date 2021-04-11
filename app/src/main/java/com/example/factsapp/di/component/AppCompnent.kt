package com.example.factsapp.di.component


import android.app.Application
import com.example.factsapp.di.module.*
import com.example.factsapp.ui.facts.FactsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [DataBaseModule::class, AppModule::class, NetworkModule::class, ViewModelModule::class, PrefModule::class])
@Singleton
interface AppComponent {

    fun inject(frag: FactsFragment)

    @Component.Builder
     interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}

