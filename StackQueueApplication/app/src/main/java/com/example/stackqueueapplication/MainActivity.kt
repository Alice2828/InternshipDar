package com.example.stackqueueapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    var obj = MyStack()
    var obj2 = MyQueue()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        val editText: EditText = findViewById(R.id.editText)

        findViewById<Button>(R.id.pushStack).setOnClickListener {
            if (editText.text.isEmpty())
                Toast.makeText(this@MainActivity, "Empty editText", Toast.LENGTH_LONG).show()
            else {
                obj.push(editText.text.toString().toInt())
                Toast.makeText(this@MainActivity, "added to Stack", Toast.LENGTH_LONG).show()
            }
            editText.setText("")
        }

        findViewById<Button>(R.id.popStack).setOnClickListener {
            try {
                textView.text = obj.pop().toString()
                Toast.makeText(this@MainActivity, "Popped Stack", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                textView.text = "Empty Stack"
            }

        }
        findViewById<Button>(R.id.topStack).setOnClickListener {
            try {
                textView.text = obj.top().toString()

            } catch (e: Exception) {
                textView.text = "Empty Stack"
            }
        }
        findViewById<Button>(R.id.emptyStack).setOnClickListener {
            if (obj.empty())
                textView.text = "Empty"
            else
                textView.text = "Not Empty"

        }
        findViewById<Button>(R.id.pushQueue).setOnClickListener {
            if (editText.text.isEmpty())
                Toast.makeText(this@MainActivity, "Empty editText", Toast.LENGTH_LONG).show()
            else {
                obj2.push(editText.text.toString().toInt())
                Toast.makeText(this@MainActivity, "added to Queue", Toast.LENGTH_LONG).show()
            }
            editText.setText("")

        }
        findViewById<Button>(R.id.popQueue).setOnClickListener {
            try {
                textView.text = obj2.pop().toString()
                Toast.makeText(this@MainActivity, "Popped Queue", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                textView.text = "Empty Queue"
            }
        }
        findViewById<Button>(R.id.peekQueue).setOnClickListener {
            try {
                textView.text = obj2.peek().toString()

            } catch (e: Exception) {
                textView.text = "Empty Queue"

            }
        }
        findViewById<Button>(R.id.emptyQueue).setOnClickListener {
            if (obj2.empty())
                textView.text = "Empty"
            else
                textView.text = "Not Empty"
        }
    }
}