package com.sundaydavid.fastBite.remoteDatabase

import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.CategoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by David
 */
interface ApiInterface {
    @GET("api/json/v1/1/search.php?")
    fun meal (@Query("f") item : String): Call<AlphabetModel>

    @GET("api/json/v1/1/categories.php")
    fun mealCategory(): Call<CategoryModel>
}