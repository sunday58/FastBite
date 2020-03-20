package com.sundaydavid.fastBite.apiclient

import com.sundaydavid.fastBite.model.AlphabetModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by David
 */
interface ApiInterface {
    @get:GET("api/json/v1/1/search.php?f=a")
    val meal: Call<ArrayList<AlphabetModel?>?>?
}