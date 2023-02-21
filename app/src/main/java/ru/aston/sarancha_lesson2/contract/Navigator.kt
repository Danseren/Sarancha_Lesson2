package ru.aston.sarancha_lesson2.contract

import androidx.fragment.app.Fragment

typealias ResultListener<T> = (T) -> Unit

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}

interface Navigator {

    fun showAboutScreen() {}

    fun showAuthorizationScreen() {}

    fun showMainScreen() {}

    fun showOfficeInfoScreen() {}

    fun showOfficesScreen() {}

    fun showVacanciesScreen() {}

    fun goBack() {}

    fun goToMenu() {}
}