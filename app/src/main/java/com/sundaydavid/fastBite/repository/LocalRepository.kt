package com.sundaydavid.fastBite.repository

import androidx.lifecycle.LiveData
import com.sundaydavid.fastBite.model.CategoryModel

interface LocalRepository {
    fun getMealCategory(): LiveData<List<CategoryModel>>
    suspend fun setMealCategory(meals: List<CategoryModel>)
}