package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.Utils.navigator
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.databinding.FragmentMainBinding

class MainFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            bottomNavigation.apply {
                setOnItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.itemMain -> {
                            navigator().showAboutScreen()
                            true
                        }
                        R.id.itemVacancies -> {
                            navigator().showVacanciesScreen()
                            true
                        }
                        R.id.itemOffices -> {
                            navigator().showOfficesScreen()
                            true
                        }
                        else -> {
                            true
                        }
                    }
                }
                selectedItemId = R.id.itemMain
            }
        }
    }


    override fun getTitleRes(): Int {

        return when (binding.bottomNavigation.selectedItemId) {
            R.id.itemMain -> R.string.titleAbout
            R.id.itemOffices -> R.string.titleOffices
            else -> R.string.titleVacancies
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}