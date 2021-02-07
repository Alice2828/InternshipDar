package com.example.tictactoe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.adapter.MyAdapter
import com.example.tictactoe.model.User
import com.example.tictactoe.model.UserList

class RecordsFragment : Fragment() {
    private lateinit var mainPage: Button
    private var userList = mutableListOf<User>()
    private lateinit var myRecyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: LinearLayoutManager

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_records, container, false)
        userList = UserList.getUserList()
        rvInit(rootView)
        mainPage = rootView.findViewById(R.id.main)
        mainPage.setOnClickListener {
            val action = RecordsFragmentDirections.actionRecordToStart()
            rootView.findNavController().navigate(action)
        }
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
        viewAdapter = MyAdapter()

        myRecyclerView.adapter = viewAdapter
        viewAdapter.submitList(userList)
    }

}