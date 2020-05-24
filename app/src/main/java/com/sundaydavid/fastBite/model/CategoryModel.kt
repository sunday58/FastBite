package com.sundaydavid.fastBite.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryModel(
    @PrimaryKey
    val categories: List<Category>
)