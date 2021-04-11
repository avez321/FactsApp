package com.example.factsapp.database

import com.example.factsapp.model.Row

class FactsRepository(val factsDao: FactsDao) {

     fun getFactsData() = factsDao.getFactsData()

    suspend fun  insertAllFacts(arraylist: List<Row>) {
        factsDao.insertAllRow(arraylist)
    }
}