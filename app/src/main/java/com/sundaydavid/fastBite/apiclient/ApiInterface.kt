package com.sundaydavid.fastBite.apiclient

import com.sundaydavid.fastBite.model.AlphabetModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by David
 */
interface ApiInterface {
    @GET("api/json/v1/1/search.php?f={item}")
    fun meal (@Path("item") item : String): Call<ArrayList<AlphabetModel?>?>?
}