package com.pnit.mobile.lab6

class CountriesProvider {
    companion object {
        fun getCountries() = listOf(
            Country(
                "Afghanistan",
                "Kabul",
                "Southern Asia",
                27657145,
                652230.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/af.png"
            ),
            Country(
                "Albania",
                "Tirana",
                "Southern Europe",
                2886026,
                28748.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/al.png"
            ),
            Country(
                "United States of America",
                "Washington, D.C.",
                "Northern America",
                323947000,
                9629091.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/us.png"
            ),
            Country(
                "United Kingdom of Great Britain and Northern Ireland",
                "London",
                "Northern Europe",
                65110000,
                242900.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/gb.png"
            ),
            Country(
                "France",
                "Paris",
                "Western Europe",
                66710000,
                640679.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/fr.png"
            ),
            Country(
                "Germany",
                "Berlin",
                "Western Europe",
                81770900,
                357114.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/de.png"
            ),
            Country(
                "Ukraine",
                "Kiev",
                "Eastern Europe",
                42692393,
                603700.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/ua.png"
            ),
            Country(
                "Italy",
                "Rome",
                "Southern Europe",
                60665551,
                301336.0,
                "https://raw.githubusercontent.com/NovelCOVID/API/master/assets/flags/it.png"
            )
        )
    }
}