package com.pnit.mobile.lab6.api

import com.pnit.mobile.lab6.data.Country

class CountryRepository {
    private var client: RestCountriesApiService = RetrofitClient.apiService

    suspend fun getCountries(): List<Country> {
        val countries = client.getAllCountries()
        countries.iterator().forEach { c ->
            c.flag =
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/${c.iso2.toLowerCase()}.png"
        }
        return countries
    }

}