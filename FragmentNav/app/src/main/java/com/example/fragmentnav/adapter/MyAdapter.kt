package com.example.fragmentnav.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentnav.R
import com.example.fragmentnav.detailInterface.ItemClickListener
import com.example.fragmentnav.model.DeletedHistory
import com.example.fragmentnav.model.Student
import com.example.fragmentnav.model.StudentList

class MyAdapter(val listener: ItemClickListener) :
    RecyclerView.Adapter<MyAdapter.RepoViewHolder>() {
    private var studentList = mutableSetOf<Student>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_card, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount() = studentList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val student = studentList.elementAt(position)
        holder.setup(student)
        holder.itemView.setOnClickListener {
            listener.itemClick(
                position,
                student
            )
        }
    }

    fun submitList(newList: MutableSet<Student>) {
        studentList = newList
        notifyDataSetChanged()
    }

    inner class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setup(student: Student?) {
            view.findViewById<TextView>(R.id.name).text = student?.name
            view.findViewById<TextView>(R.id.id).text = student?.id.toString()
            view.findViewById<Button>(R.id.delete).setOnClickListener {
                removeAt(adapterPosition);
            }
        }

    }

    fun removeAt(position: Int) {
        DeletedHistory.addDeleted(studentList.elementAt(position))
        studentList.remove(studentList.elementAt(position))
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, studentList.size)
    }

}
