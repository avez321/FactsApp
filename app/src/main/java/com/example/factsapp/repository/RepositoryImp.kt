package com.example.factsapp.repository


import com.example.factsapp.Constants
import com.example.factsapp.model.Fact
import com.example.factsapp.network.ResultWrapper
import com.example.factsapp.network.safeApiCall
import com.example.factsapp.network.FactsApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RepositoryImp(private val factsApi: FactsApi, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
    RepositoryInterface {
    override suspend fun getFactsFeeds(): ResultWrapper<Fact> {

        return safeApiCall(dispatcher) { factsApi.getFacts( Constants.country, Constants.apiKey) }
    }
}


