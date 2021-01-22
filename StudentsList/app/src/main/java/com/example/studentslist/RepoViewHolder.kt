package com.example.studentslist


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RepoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    fun setup(itemData: Student?) {
        view.findViewById<TextView>(R.id.name).text=itemData?.name
        view.findViewById<TextView>(R.id.surname).text=itemData?.surname
    }
}