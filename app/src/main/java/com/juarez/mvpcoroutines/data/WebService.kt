package com.juarez.mvpcoroutines.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WebService {

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    fun createUserApi(): UserApi = createRetrofit().create(UserApi::class.java)
}