package ru.aston.sarancha_lesson2

import ru.aston.sarancha_lesson2.Utils.WHITE_COLLAR

data class Vacancy(
    override val headerText: String = "Title",
    override val bodyText: String = "Title",
    override val imageSrc: Int,
    override val type: Int = WHITE_COLLAR,
    val language: String
) : MyViewDto()