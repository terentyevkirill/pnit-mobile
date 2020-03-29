package com.pnit.mobile.lab6.data

import androidx.room.*
import com.pnit.mobile.lab6.data.Country

@Dao
interface CountryDao {
    @Query("SELECT * FROM countries")
    fun getAll(): List<Country>

    @Query("SELECT * FROM countries WHERE name LIKE :name")
    fun getByName(name: String): Country

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg countries: Country)

    @Delete
    fun delete(country: Country)
}