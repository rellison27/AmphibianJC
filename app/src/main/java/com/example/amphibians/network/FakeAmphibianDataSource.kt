package com.example.amphibians.network

import com.example.amphibians.model.Amphibian

object FakeAmphibianDataSource
{
        val mockData = List(10) { index ->
            Amphibian(
                id = index,
                name = "Great Basin Spadefoot",
                type = "Toad",
                description = "This toad is typically found in South America. Specifically on Mount Roraima at the boarders of Venezuala, Brazil, and Guyana, hence the name. The Roraiam Bush Toad is typically black with yellow spots or marbling along the throat and belly.",
                imgSrc = "https://developer.android.com/codelabs/basic-android-kotlin-compose-amphibians-app/img/great-basin-spadefoot.png"
            )
        }
}