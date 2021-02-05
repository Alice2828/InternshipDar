package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class StartFragment : Fragment() {
    private lateinit var newGame: Button
    private lateinit var records: Button

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_start, container, false)
        newGame = rootView.findViewById(R.id.newGame)
        records = rootView.findViewById(R.id.records)
        newGame.setOnClickListener {
            val action = StartFragmentDirections.actionStartToNew()
            rootView.findNavController().navigate(action)
        }
        records.setOnClickListener {
            val action = StartFragmentDirections.actionStartToRecords()
            rootView.findNavController().navigate(action)
        }
        return rootView
    }
}