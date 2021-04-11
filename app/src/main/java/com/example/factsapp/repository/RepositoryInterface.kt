package com.example.factsapp.repository

import com.example.factsapp.model.Fact
import com.example.factsapp.network.ResultWrapper

interface RepositoryInterface {
   suspend fun getFactsFeeds(): ResultWrapper<Fact>
}