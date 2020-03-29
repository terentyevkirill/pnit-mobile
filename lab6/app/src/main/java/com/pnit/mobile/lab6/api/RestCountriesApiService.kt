package com.pnit.mobile.lab6.api

import com.google.gson.GsonBuilder
import com.pnit.mobile.lab6.data.Country
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RestCountriesApiService {
    @GET("all")
    suspend fun getAllCountries(
        @Query("fields") fields: String = "name;capital;subregion;population;area;alpha2Code"
    ): List<Country>
}

