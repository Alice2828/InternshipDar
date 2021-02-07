package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.tictactoe.model.User
import com.example.tictactoe.model.UserList

class NewGameFragment : Fragment() {
    private lateinit var play: Button
    private lateinit var user1: EditText
    private lateinit var user2: EditText

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_new_game, container, false)
        play = rootView.findViewById(R.id.play)
        user1 = rootView.findViewById(R.id.user1)
        user2 = rootView.findViewById(R.id.user2)
        play.setOnClickListener {
            if (user1.text.isEmpty() || user2.text.isEmpty()) {
                Toast.makeText(context, "You should write names", Toast.LENGTH_LONG).show()
            } else if (user1.text.toString() == user2.text.toString()) {
                Toast.makeText(context, "Names should be different", Toast.LENGTH_LONG).show()
            } else {
                if (!UserList.getUserList().contains(User(user1.text.toString())))
                    UserList.addUser(User(user1.text.toString()))
                if (!UserList.getUserList().contains(User(user2.text.toString())))
                    UserList.addUser(User(user2.text.toString()))
                val action = NewGameFragmentDirections.actionNewToGame(user1.text.toString(), user2.text.toString())
                rootView.findNavController().navigate(action)
            }
        }
        return rootView
    }
}