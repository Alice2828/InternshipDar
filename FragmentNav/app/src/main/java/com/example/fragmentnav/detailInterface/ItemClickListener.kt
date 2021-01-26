package com.example.fragmentnav.detailInterface

import com.example.fragmentnav.model.Student

interface ItemClickListener {
    fun itemClick(position: Int, student: Student?)
}