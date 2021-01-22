package com.example.studentslist

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Student(val name: String, val surname: String) : Parcelable
