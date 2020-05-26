package com.sundaydavid.fastBite.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)