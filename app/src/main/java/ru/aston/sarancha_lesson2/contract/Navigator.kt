package ru.aston.sarancha_lesson2.contract

import ru.aston.sarancha_lesson2.OfficeAddress

interface Navigator {

    fun showAboutScreen()

    fun showAuthorizationScreen()

    fun showMainScreen()

    fun showOfficeInfoScreen(officeAddress: OfficeAddress)

    fun showOfficesScreen()

    fun showVacanciesScreen()

    fun goBack()
}