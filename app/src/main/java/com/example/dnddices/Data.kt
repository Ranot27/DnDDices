package com.example.dnddices

import androidx.annotation.DrawableRes

data class DiceClass(
    val max: Int,
    @DrawableRes val image: Int
)


val dices = listOf<DiceClass>(
    DiceClass(4, R.drawable.d4),
    DiceClass(6, R.drawable.d6),
    DiceClass(8, R.drawable.d8),
    DiceClass(10, R.drawable.d10),
    DiceClass(12, R.drawable.d12),
    DiceClass(20, R.drawable.d20)
)



