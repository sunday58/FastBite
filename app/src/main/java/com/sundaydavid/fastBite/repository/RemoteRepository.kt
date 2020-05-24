package com.sundaydavid.fastBite.repository

import com.sundaydavid.fastBite.model.CategoryModel
import retrofit2.Call

interface RemoteRepository {

    fun getCategory(): Call<CategoryModel>
}