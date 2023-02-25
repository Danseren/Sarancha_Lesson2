package ru.aston.sarancha_lesson2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.aston.sarancha_lesson2.Utils.MOTHERLAND

@Parcelize
data class OfficeAddress(
    override val headerText: String,
    override val bodyText: String,
    override val imageSrc: Int,
    override val type: Int = MOTHERLAND,
    val address: String
) : MyViewDto(), Parcelable

sealed class MyViewDto {
    abstract val headerText: String
    abstract val bodyText: String
    abstract val imageSrc: Int
    abstract val type: Int
}