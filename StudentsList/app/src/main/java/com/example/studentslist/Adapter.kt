package com.example.studentslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(var repoList: List<Student>? = null) :
    RecyclerView.Adapter<RepoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_card, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = repoList?.size ?: 0

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.setup(repoList?.get(position))

    }

    fun updateRepoList(repoList: List<Student>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}
