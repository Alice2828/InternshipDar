package com.example.fragmentnav.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.fragmentnav.R
import com.example.fragmentnav.model.Student
import com.example.fragmentnav.adapter.MyAdapter
import com.example.fragmentnav.detailInterface.ItemClickListener
import com.example.fragmentnav.model.StudentList

class FragmentFirst : Fragment() {
    var studentList = mutableSetOf<Student>()
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: LinearLayoutManager
    private var listener: ItemClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_first, container, false)
        studentList = StudentList.getStudentList()
        createDetailsListener(rootView)
        rvInit(rootView)
        implementAdd(rootView)
        return rootView
    }

    private fun rvInit(rootView: View) {
        //recyclerView init
        myRecyclerView = rootView.findViewById(R.id.myRecyclerView)
        //linearLayoutManager for rv
        viewManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = viewManager
        val dividerItemDecoration = DividerItemDecoration(
            myRecyclerView.context,
            viewManager.orientation
        )
        myRecyclerView.addItemDecoration(dividerItemDecoration)
        //create adapter, to recyclerview
        viewAdapter = listener?.let { MyAdapter(it) }!!

        myRecyclerView.adapter = viewAdapter
        viewAdapter.submitList(studentList)
    }

    private fun createDetailsListener(rootView: View) {
        listener = object : ItemClickListener {
            override fun itemClick(position: Int, student: Student?) {
                val action = FragmentFirstDirections.actionFirstToSecond(student!!)
                rootView.findNavController().navigate(action)
            }
        }
    }

    private fun implementAdd(rootView: View) {
        val nameText = rootView.findViewById<EditText>(R.id.addName)
        rootView.findViewById<Button>(R.id.add).setOnClickListener {
            if (nameText.text.isEmpty()) {
                Toast.makeText(context, "Empty name", Toast.LENGTH_LONG).show()
            } else {
                StudentList.addStudent(
                    Student(
                        StudentList.getStudentList().size,
                        nameText.text.toString(),
                        null,
                        null,
                        null
                    )
                )
                viewAdapter.submitList(studentList)
                rootView.findViewById<SwipeRefreshLayout>(R.id.swipe).isRefreshing = true
                rootView.findViewById<SwipeRefreshLayout>(R.id.swipe).isRefreshing = false
            }
        }
    }

}