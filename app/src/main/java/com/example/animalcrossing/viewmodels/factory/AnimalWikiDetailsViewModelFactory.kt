package com.example.animalcrossing.viewmodels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.animalcrossing.viewmodels.AnimalWikiDetailsViewModel

class AnimalWikiDetailsViewModelFactory(private val animalId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnimalWikiDetailsViewModel::class.java)) {
            return AnimalWikiDetailsViewModel(animalId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}