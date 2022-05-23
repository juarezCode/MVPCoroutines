package com.juarez.mvpcoroutines

import com.juarez.mvpcoroutines.data.WebService
import com.juarez.mvpcoroutines.models.User

class UserRepository {
    suspend fun getUsers(): List<User> {
        val response = WebService.createUserApi().getUsers()
        return response.body()!!
    }

    suspend fun getUserByIdService(userId: Int): User {
        val response = WebService.createUserApi().getUserById(userId)
        return response.body()!!
    }
}