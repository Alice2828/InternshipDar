package com.example.tictactoe.model

object UserList {
    private var userList = mutableListOf<User>()

    fun addUser(user: User) {
        userList.add(user)
    }

    fun getUserList(): MutableList<User> {
        return userList
    }

    fun findByName(userName: String): User? {
        for (i in userList) {
            if (i.userName == userName) return i
        }
        return null
    }
}