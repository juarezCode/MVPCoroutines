package com.juarez.mvpcoroutines.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService {
    private fun getClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        
        return OkHttpClient.Builder().addInterceptor(logging).build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun createUserApi(): UserApi = createRetrofit().create(UserApi::class.java)
}