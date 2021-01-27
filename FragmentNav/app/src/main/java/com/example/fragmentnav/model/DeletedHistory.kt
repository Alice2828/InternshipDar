package com.example.fragmentnav.model

object DeletedHistory {
    private lateinit var deletedList: ArrayDeque<Student>
    fun createHistory() {
        deletedList = ArrayDeque()
    }

    fun addDeleted(student: Student) {
        if (deletedList.size < 5)
            deletedList.add(student)
        else {
            deletedList.remove(deletedList.first())
            deletedList.add(student)
        }
    }

    fun restoreDeleted(): Student {
        val lastDeleted = deletedList.last()
        deletedList.remove(lastDeleted)
        return lastDeleted
    }
}