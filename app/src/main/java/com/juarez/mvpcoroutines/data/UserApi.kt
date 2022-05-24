package com.juarez.mvpcoroutines.data

import com.juarez.mvpcoroutines.models.Album
import com.juarez.mvpcoroutines.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("albums")
    suspend fun getAlbumsByUserId(@Query("userId") userId: Int): Response<List<Album>>

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): Response<User>
}