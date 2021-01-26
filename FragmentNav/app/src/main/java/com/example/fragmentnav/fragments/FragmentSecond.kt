package com.example.fragmentnav.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.fragmentnav.R


class FragmentSecond : Fragment() {
    val args: FragmentSecondArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_second, container, false)
        val student = args.student
        rootView.findViewById<TextView>(R.id.idStudent).text = student.id.toString()
        rootView.findViewById<TextView>(R.id.name).text = student.name
        rootView.findViewById<TextView>(R.id.surname).text = student.surname
        rootView.findViewById<TextView>(R.id.grade).text = student.grade.toString()

        return rootView
    }

}