package com.example.petadopt.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.petadopt.data.Pet
import com.example.petadopt.data.PetDataSource

class AdoptViewModel : ViewModel() {
    fun getPet(id: Int): Pet = PetDataSource().getPet(id) ?: Pet()
}