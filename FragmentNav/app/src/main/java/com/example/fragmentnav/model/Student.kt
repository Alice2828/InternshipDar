package com.example.fragmentnav.model

import android.os.Parcelable
import android.util.Base64
import kotlinx.android.parcel.Parcelize

import java.util.*

@Parcelize
data class Student(
    val id: Int = 0,
    val name: String,
    val surname: String?,
    val grade: Double?,
    val stImage: String?
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student

        return name == other.name
    }

    override fun hashCode(): Int {
        return Objects.hash(name)
    }
}
