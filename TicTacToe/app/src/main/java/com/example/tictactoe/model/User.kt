package com.example.tictactoe.model

import java.util.*

data class User(var userName: String) {
    private var win: Int = 0
    private var lose: Int = 0
    fun increaseWin() {
        win += 1
    }

    fun increaseLose() {
        lose += 1
    }

    fun getWin(): Int {
        return win
    }

    fun getLose(): Int {
        return lose
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return userName == other.userName
    }

    override fun hashCode(): Int {
        return Objects.hash(userName)
    }
}