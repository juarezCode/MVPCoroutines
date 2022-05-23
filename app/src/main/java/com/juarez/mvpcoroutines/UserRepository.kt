package com.juarez.mvpcoroutines

import com.juarez.mvpcoroutines.models.User
import kotlinx.coroutines.delay

class UserRepository {
    private val users = listOf(User(1, "Jose"), User(2, "Roberto"))
    suspend fun getUsers(): List<User> {
        delay(3000)
        return users
    }

    suspend fun getUserByIdService(userId: Int): User {
        delay(3000)
        return users.find { user -> user.id == userId }!!
    }
}