package com.example.petadopt.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.petadopt.data.PetDataSource

class ListViewModel : ViewModel() {
    val petList = PetDataSource().loadPets()
}