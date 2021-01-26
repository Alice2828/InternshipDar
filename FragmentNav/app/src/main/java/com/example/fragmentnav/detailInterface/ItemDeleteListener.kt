package com.example.fragmentnav.detailInterface

import com.example.fragmentnav.model.Student

interface ItemDeleteListener {
    fun itemDelete(position: Int, item: Student?)
}