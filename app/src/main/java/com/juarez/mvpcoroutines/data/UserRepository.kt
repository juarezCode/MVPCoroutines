package com.juarez.mvpcoroutines.data

import com.juarez.mvpcoroutines.models.Album
import com.juarez.mvpcoroutines.models.User

class UserRepository {
    suspend fun getUsers(): List<User> {
        val response = WebService.createUserApi().getUsers()
        return response.body()!!
    }

    suspend fun getAlbumsByUserId(userId: Int): List<Album> {
        val response = WebService.createUserApi().getAlbumsByUserId(userId)
        return response.body()!!
    }

    suspend fun getUserByIdService(userId: Int): User {
        val response = WebService.createUserApi().getUserById(userId)
        return response.body()!!
    }
}