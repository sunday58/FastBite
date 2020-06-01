package com.sundaydavid.fastBite.model

import java.io.Serializable

data class SearchModel(
    val meals: List<Meal>
): Serializable