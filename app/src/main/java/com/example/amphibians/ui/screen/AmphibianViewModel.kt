package com.example.amphibians.ui.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibianApplication
import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.launch


class AmphibianViewModel(private val amphibianRepository: AmphibianRepository): ViewModel()
{
    var amphibianUiState:List<Amphibian> by mutableStateOf(listOf())
    private set

    init
    {
        getAmphibians()
    }

     private fun getAmphibians(): List<Amphibian>
    {
        viewModelScope.launch {
           amphibianUiState = amphibianRepository.getAmphibians()
//            amphibianUiState = try
//            {
//                amphibianRepository.getAmphibians()
//            } catch (e: IOException) {
//              Log.e("VIEW_MODEL", e)
//            }
        }
        return amphibianUiState
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AmphibianApplication)
                val amphibianRepository= application.container.amphibianRepository
                AmphibianViewModel(amphibianRepository = amphibianRepository)
            }
        }
    }
}