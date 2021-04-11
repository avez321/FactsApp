package com.example.factsapp

import androidx.multidex.MultiDexApplication
import com.example.factsapp.di.component.DaggerAppComponent


class FactsApp : MultiDexApplication() {

    lateinit var appComponent: DaggerAppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build() as DaggerAppComponent

    }

}