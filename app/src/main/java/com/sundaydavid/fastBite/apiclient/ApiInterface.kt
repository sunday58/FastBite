package com.sundaydavid.fastBite.apiclient

import com.sundaydavid.fastBite.model.AlphabetModel
import com.sundaydavid.fastBite.model.Meal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by David
 */
interface ApiInterface {
    @GET("api/json/v1/1/search.php?")
    fun meal (@Query(("f")) item : String): Call<AlphabetModel>
}