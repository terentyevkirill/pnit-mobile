package com.pnit.mobile.lab6.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pnit.mobile.lab6.api.CountryRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel: ViewModel() {
    val repo: CountryRepository = CountryRepository()

    val countries = liveData(Dispatchers.IO) {
        val retrievedCountries = repo.getCountries()
        emit(retrievedCountries)
    }
}