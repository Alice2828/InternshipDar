package com.example.fragmentnav.fragments

import android.content.Context.MODE_PRIVATE
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
import com.example.fragmentnav.model.DeletedHistory
import com.example.fragmentnav.model.StudentList
import java.lang.Exception

class FragmentFirst : Fragment() {
    var studentList = mutableSetOf<Student>()
    private var lastPosition: Int = 0
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: LinearLayoutManager
    private var listener: ItemClickListener? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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
        implementRestore(rootView)
        swipeFunction(rootView)
        return rootView
    }

    private fun rvInit(rootView: View) {
        //recyclerView init
        myRecyclerView = rootView.findViewById(R.id.myRecyclerView)
        //linearLayoutManager for rv
        viewManager = LinearLayoutManager(context)
        myRecyclerView.layoutManager = viewManager
        findLastPosition()
        saveScrollRv()
        val dividerItemDecoration = DividerItemDecoration(
            myRecyclerView.context,
            viewManager.orientation
        )
        myRecyclerView.addItemDecoration(dividerItemDecoration)
        //create adapter, to recyclerview
        viewAdapter = listener?.let { MyAdapter(it) }!!

        myRecyclerView.adapter = viewAdapter
//        viewAdapter.stateRestorationPolicy =
//            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
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
                StudentList.increaseCounter()
                StudentList.addStudent(
                    Student(
                        StudentList.getCounter(),
                        nameText.text.toString(),
                        null,
                        null,
                        null
                    )
                )
                nameText.setText("")
                viewAdapter.submitList(studentList)
                swipeRefreshLayout.isRefreshing = true
                swipeRefreshLayout.isRefreshing = false

            }
        }
    }

    private fun implementRestore(rootView: View) {
        rootView.findViewById<Button>(R.id.restore).setOnClickListener {
            try {
                studentList.add(DeletedHistory.restoreDeleted())
                viewAdapter.submitList(studentList)
            } catch (e: Exception) {
                Toast.makeText(context, "Empty history", Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun saveScrollRv() {

        myRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                lastPosition = viewManager.findLastVisibleItemPosition()
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        val getPrefs = context?.getSharedPreferences("scroll", MODE_PRIVATE)
        val editor = getPrefs?.edit()
        editor?.putInt("lastPos", lastPosition)
        editor?.apply()
    }

    private fun findLastPosition() {
        val getPrefs = context?.getSharedPreferences("scroll", MODE_PRIVATE)
        if (getPrefs != null) {
            lastPosition = getPrefs.getInt("lastPos", 0)
        }
        myRecyclerView.scrollToPosition(lastPosition)
    }

    private fun swipeFunction(rootView: View) {
        swipeRefreshLayout = rootView.findViewById(R.id.swipe)
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = true
            swipeRefreshLayout.isRefreshing = false

        }
    }
}