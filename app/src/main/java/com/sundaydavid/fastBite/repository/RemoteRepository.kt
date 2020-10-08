package com.sundaydavid.fastBite.repository

import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.CategoryModel
import com.sundaydavid.fastBite.model.SearchModel
import retrofit2.Call

interface RemoteRepository {

    fun getCategory(): Call<CategoryModel>
    fun getMeal(item: String): Call<AlphabetModel>
}