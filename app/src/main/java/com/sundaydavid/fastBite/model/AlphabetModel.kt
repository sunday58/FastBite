package com.sundaydavid.fastBite.model

import java.io.Serializable

data class AlphabetModel(
    val meals: List<Meal>
) : Serializable