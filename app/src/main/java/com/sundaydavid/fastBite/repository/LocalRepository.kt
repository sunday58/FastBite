package com.sundaydavid.fastBite.repository

import androidx.lifecycle.LiveData
import com.sundaydavid.fastBite.model.Category
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.model.Meal

interface LocalRepository {
    fun getMealCategory(): LiveData<List<Category>>
    suspend fun setMealCategory(meals: List<Category>)
}