package com.sundaydavid.fastBite.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sundaydavid.fastBite.database.local.storage.MealDatabase
import com.sundaydavid.fastBite.model.*
import com.sundaydavid.fastBite.remoteDatabase.ApiClient
import com.sundaydavid.fastBite.remoteDatabase.ApiInterface
import retrofit2.Call

class MainRepository(private val api: ApiInterface,  private val database: MealDatabase) :
RemoteRepository, LocalRepository{
    override fun getCategory(): Call<CategoryModel> {
        return api.mealCategory()
    }

    override fun getMeal(item: String): Call<AlphabetModel> {
        TODO("Not yet implemented")
    }

    override fun getMealCategory(): LiveData<List<Category>> {
        return database.mealDao.getCategory()
    }

    override suspend fun setMealCategory(meals: List<Category>) {
      database.mealDao.insertAll(meals)
    }
}