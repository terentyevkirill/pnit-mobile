package com.pnit.mobile.lab6.api

import com.pnit.mobile.lab6.data.Country
import retrofit2.Call
import retrofit2.http.GET

interface RestApiService {
    @GET("all?fields=name;capital;subregion;population;area;alpha2Code")
    fun getCountriesData(): Call<List<Country>>
}