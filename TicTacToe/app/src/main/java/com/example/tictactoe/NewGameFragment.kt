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
        play.setOnClickListener{
            if(user1.text.isEmpty() || user2.text.isEmpty()){
                Toast.makeText(context, "You should write nemes", Toast.LENGTH_LONG).show()
            }
            else{
                val action = NewGameFragmentDirections.actionNewToGame(user1.text.toString(), user2.text.toString())
                rootView.findNavController().navigate(action)
            }
        }
        return rootView
    }
}