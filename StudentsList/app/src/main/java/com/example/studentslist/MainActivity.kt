package com.example.studentslist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    private var studentList = ArrayList<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..10) {
            studentList.add(Student("Name$i", "Surname$i"))
        }
        val nameText = findViewById<EditText>(R.id.name)
        val surnameText = findViewById<EditText>(R.id.surname)

        findViewById<TextView>(R.id.total).text = studentList.size.toString()
        findViewById<Button>(R.id.add).setOnClickListener {
            if (nameText.text.isEmpty() || surnameText.text.isEmpty()) {
                Toast.makeText(this@MainActivity, "Empty name or surname", Toast.LENGTH_LONG).show()
            } else if (studentList.contains(
                    Student(
                        nameText.text.toString(),
                        surnameText.text.toString()
                    )
                )

            ) {
                Toast.makeText(this@MainActivity, "Student is already in list", Toast.LENGTH_LONG)
                    .show()

            } else {
                studentList.add(
                    Student(
                        nameText.text.toString(),
                        surnameText.text.toString()
                    )
                )
                findViewById<SwipeRefreshLayout>(R.id.swipe).isRefreshing = true
                findViewById<SwipeRefreshLayout>(R.id.swipe).isRefreshing = false

                findViewById<TextView>(R.id.total).text = studentList.size.toString()
            }
        }
        findViewById<Button>(R.id.second).setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelableArrayList("studentList", studentList)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}