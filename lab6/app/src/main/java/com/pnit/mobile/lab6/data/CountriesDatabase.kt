package com.pnit.mobile.lab6.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        private var instance: CountriesDatabase? = null
        fun getInstance(context: Context): CountriesDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    CountriesDatabase::class.java,
                    "countriesdb"
                ).build()
            }

            return instance as CountriesDatabase
        }
    }
}