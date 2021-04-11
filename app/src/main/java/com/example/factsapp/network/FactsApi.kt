package com.example.factsapp.network

import com.example.factsapp.model.Fact
import retrofit2.http.GET
import retrofit2.http.Query

interface FactsApi {
    @GET("/s/2iodh4vg0eortkl/facts.json")
    suspend fun getFacts(@Query("country") country:String, @Query("apiKey") apiKey: String): Fact
}