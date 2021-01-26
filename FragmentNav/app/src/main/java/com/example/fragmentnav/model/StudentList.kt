package com.example.fragmentnav.model

object StudentList {
    private var studentList = mutableSetOf<Student>()

     fun createStudentsList() {
        for (i in 0..10) {
            studentList.add(Student(i, "Name$i", "Surname$i", i.toDouble(), ""))
        }
    }

     fun addStudent(student: Student) {
        studentList.add(student)
    }

     fun deleteStudent(id: Int) {
        studentList.remove(studentList.elementAt(id))
    }

     fun getStudentList(): MutableSet<Student> {
        return studentList
    }
}