package com.example.factsapp.model


import com.example.factsapp.network.FactsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object FactsClient {
    val BASE_URL = "https://dl.dropboxusercontent.com"

    fun makeFactsApi(): FactsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build().create(FactsApi::class.java)
    }

    fun getOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }
}