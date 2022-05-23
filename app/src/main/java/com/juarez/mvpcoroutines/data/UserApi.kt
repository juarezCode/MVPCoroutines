package com.juarez.mvpcoroutines.data

import com.juarez.mvpcoroutines.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): Response<User>
}