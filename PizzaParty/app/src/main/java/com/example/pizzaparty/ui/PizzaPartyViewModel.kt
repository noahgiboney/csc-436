package com.example.pizzaparty.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pizzaparty.HungerLevel
import com.example.pizzaparty.PizzaCalculator

class PizzaPartyViewModel: ViewModel() {

    var hungerLevel by mutableStateOf(HungerLevel.MEDIUM)
    var numPeople by mutableStateOf("")

    var totalPizza by mutableIntStateOf(0)
        private set

    fun calcPizzas() {
        Log.i("numberPeople", numPeople)
        val calc = PizzaCalculator(numPeople.toIntOrNull() ?: 0, hungerLevel)
        Log.i("logger", "${calc.totalPizzas}")
        totalPizza = calc.totalPizzas
    }
}