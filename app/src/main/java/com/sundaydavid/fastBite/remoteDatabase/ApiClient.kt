package com.sundaydavid.fastBite.remoteDatabase

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by David
 */
object ApiClient {
    private var BASE_URL: String = "https://www.themealdb.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getClient: ApiInterface = retrofit.create(ApiInterface::class.java)
}