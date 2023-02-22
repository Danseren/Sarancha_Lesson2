package ru.aston.sarancha_lesson2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OfficeAddress(
    val country: String,
    val city: String,
    val address: String
) : Parcelable
