package com.sundaydavid.fastBite.apiclient

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
     val getClient: ApiInterface
            get() {
                val gson = GsonBuilder()
                    .setLenient()
                    .create()
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                return retrofit.create(ApiInterface::class.java)

            }
}