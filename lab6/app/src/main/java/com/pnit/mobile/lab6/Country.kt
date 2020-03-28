package com.pnit.mobile.lab6

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    var name: String,
    var capital: String,
    @SerializedName("subregion")
    var region: String,
    var population: Long,
    var area: Double,
    @SerializedName("alpha2Code")
    var iso2: String,
    @Expose
    var flag: String
)