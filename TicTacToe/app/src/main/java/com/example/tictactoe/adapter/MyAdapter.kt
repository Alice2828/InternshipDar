package com.example.tictactoe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.R
import com.example.tictactoe.model.User

class MyAdapter :
    RecyclerView.Adapter<MyAdapter.RepoViewHolder>() {
    private var userList = mutableListOf<User>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.record_item, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val student = userList.elementAt(position)
        holder.setup(student)
    }

    fun submitList(newList: MutableList<User>) {
        userList = newList
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setup(user: User?) {
            view.findViewById<TextView>(R.id.user).text = user?.userName
            view.findViewById<TextView>(R.id.win).text = user?.getWin().toString()
            view.findViewById<TextView>(R.id.lose).text = user?.getLose().toString()
        }

    }
}
