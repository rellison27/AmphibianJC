package com.example.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.FakeAmphibianDataSource


class AmphibianViewModel: ViewModel()
{
    var amphibianUiState:List<Amphibian> by mutableStateOf(listOf())
    private set

    init
    {
        getAmphibians()
    }

     private fun getAmphibians(): List<Amphibian>
    {
        amphibianUiState = FakeAmphibianDataSource.mockData
        return amphibianUiState
    }
}