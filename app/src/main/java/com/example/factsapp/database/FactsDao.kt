package com.example.factsapp.database

import androidx.room.*
import com.example.factsapp.model.*

@Dao
interface FactsDao {
    @Transaction
    @Query("SELECT * FROM facts_table")
    fun getFactsData(): List<Row>


    @Insert
    suspend fun insertAllRow(rowList: List<Row>)
}