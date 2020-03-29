package com.pnit.mobile.lab6.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey
    var name: String,
    var capital: String,
    @SerializedName("subregion")
    var region: String,
    var population: Int,
    var area: Double,
    @SerializedName("alpha2Code")
    var iso2: String,
    @Expose
    var flag: String
)