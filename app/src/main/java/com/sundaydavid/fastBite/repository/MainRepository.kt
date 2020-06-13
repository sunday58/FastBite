package com.sundaydavid.fastBite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sundaydavid.fastBite.database.local.storage.MealDatabase
import com.sundaydavid.fastBite.model.Category
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.model.Meal
import com.sundaydavid.fastBite.model.SearchModel
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.remoteDatabase.ApiInterface
import retrofit2.Call

class MainRepository(private val api: ApiInterface,  private val database: MealDatabase) :
RemoteRepository, LocalRepository{
    override fun getCategory(): Call<CategoryModel> {
        return api.mealCategory()
    }


    override fun getMealCategory(): LiveData<List<Category>> {
        return database.mealDao.getCategory()
    }

    override suspend fun setMealCategory(meals: List<Category>) {
      database.mealDao.insertAll(meals)
    }


}