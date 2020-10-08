package com.sundaydavid.fastBite.repository

import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.remoteDatabase.ApiInterface
import retrofit2.Call

class MealRepository(private val api: ApiInterface) : RemoteRepository {
    override fun getCategory(): Call<CategoryModel> {
        TODO("Not yet implemented")
    }

    override fun getMeal(item: String): Call<AlphabetModel> {
        return api.meal(item)
    }
}