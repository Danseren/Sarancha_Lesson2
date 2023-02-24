package ru.aston.sarancha_lesson2

import ru.aston.sarancha_lesson2.Utils.WHITE_COLLAR

data class Vacancy(
    val headerText: String = "Title",
    val bodyText: String = "Title",
    val imageSrc: Int,
    val language: String,
    val type: Int = WHITE_COLLAR
)