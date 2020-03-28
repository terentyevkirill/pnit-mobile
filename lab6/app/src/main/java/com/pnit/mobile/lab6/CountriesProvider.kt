package com.pnit.mobile.lab6

class CountriesProvider {
    companion object {
        fun getCountries() = listOf(
            Country(
                "Afghanistan",
                "Kabul",
                "Asia",
                "Southern Asia",
                27657145,
                652230.0,
                "https://restcountries.eu/data/afg.svg"
            ),
            Country(
                "Albania",
                "Tirana",
                "Europe",
                "Southern Europe",
                2886026,
                28748.0,
                "https://restcountries.eu/data/alb.svg"
            ),
            Country(
                "United States of America",
                "Washington, D.C.",
                "Americas",
                "Northern America",
                323947000,
                9629091.0,
                "https://restcountries.eu/data/usa.svg"
            ),
            Country(
                "United Kingdom of Great Britain and Northern Ireland",
                "London",
                "Europe",
                "Northern Europe",
                65110000,
                242900.0,
                "https://restcountries.eu/data/gbr.svg"
            ),
            Country(
                "France",
                "Paris",
                "Europe",
                "Western Europe",
                66710000,
                640679.0,
                "https://restcountries.eu/data/fra.svg"
            ),
            Country(
                "Germany",
                "Berlin",
                "Europe",
                "Western Europe",
                81770900,
                357114.0,
                "https://restcountries.eu/data/deu.svg"
            ),
            Country(
                "Ukraine",
                "Kiev",
                "Europe",
                "Eastern Europe",
                42692393,
                603700.0,
                "https://restcountries.eu/data/ukr.svg"
            ),
            Country(
                "Italy",
                "Rome",
                "Europe",
                "Southern Europe",
                60665551,
                301336.0,
                "https://restcountries.eu/data/ita.svg"
            )
        )
    }
}