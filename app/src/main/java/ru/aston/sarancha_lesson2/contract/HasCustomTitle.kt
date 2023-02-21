package ru.aston.sarancha_lesson2.contract

import androidx.annotation.StringRes

interface HasCustomTitle {

    @StringRes
    fun getTitleRes(): Int
}