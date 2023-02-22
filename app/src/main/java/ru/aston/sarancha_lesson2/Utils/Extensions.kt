package ru.aston.sarancha_lesson2.Utils

import android.view.View
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.contract.Navigator

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}