package com.example.dnddices

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import kotlin.random.Random

class DnDDicesViewModel(): ViewModel() {

    var uiState by mutableStateOf(DnDDicesState())
        private set


    fun onPlusClicked(){
        uiState = uiState.copy(numberOfRolls = uiState.numberOfRolls + 1)
    }
    fun onMinusClicked(){
        if (uiState.numberOfRolls > 1) {
            uiState = uiState.copy(numberOfRolls = uiState.numberOfRolls - 1)
        }
    }

    suspend fun rollDice(max: Int): Int {
        var result = 0
        repeat(uiState.numberOfRolls){
            result += Random.nextInt(1, max + 1)
        }
        return result
    }
}