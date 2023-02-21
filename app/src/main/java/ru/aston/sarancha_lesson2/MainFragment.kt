package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.databinding.FragmentMainBinding

class MainFragment : Fragment() {

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
                            childFragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, AboutFragment.newInstance())
                                .commit()
                            true
                        }
                        R.id.itemVacancies -> {
                            childFragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, VacanciesFragment.newInstance())
                                .commit()
                            true
                        }
                        R.id.itemOffices -> {
                            childFragmentManager.beginTransaction()
                                .replace(R.id.fragmentContainer, OfficesFragment.newInstance())
                                .commit()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}