package com.sundaydavid.fastBite.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "categoryMeal")
data class CategoryModel(
    @PrimaryKey()
    @TypeConverters(TypeConverter::class)
    val categories: List<Category>
)