package com.example.factsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.factsapp.model.Row


@Database(entities = arrayOf(Row::class), version = 1, exportSchema = false)
 abstract class FactsDatabase : RoomDatabase() {

    abstract fun factsDao(): FactsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FactsDatabase? = null

        fun getDatabase(context: Context): FactsDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FactsDatabase::class.java,
                    "facts_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}