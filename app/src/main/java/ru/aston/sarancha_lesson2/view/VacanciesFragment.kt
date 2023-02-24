package ru.aston.sarancha_lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.*
import ru.aston.sarancha_lesson2.Utils.BLUE_COLLAR
import ru.aston.sarancha_lesson2.Utils.GOLD_COLLAR
import ru.aston.sarancha_lesson2.Utils.WHITE_COLLAR
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.databinding.FragmentVacanciesBinding

class VacanciesFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentVacanciesBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = VacanciesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVacanciesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vacancies = arrayListOf(

            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.frontend),
                getString(R.string.frontendDescription),
                R.drawable.worker_64,
                getString(R.string.java),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.teamLead),
                getString(R.string.teamLeadDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.deputyDirector),
                getString(R.string.deputyDirectorDescription),
                R.drawable.structure,
                getString(R.string.none),
                GOLD_COLLAR
            ),
            Vacancy(
                getString(R.string.cleaner),
                getString(R.string.cleanerDescription),
                R.drawable.household,
                getString(R.string.none),
                BLUE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.frontend),
                getString(R.string.frontendDescription),
                R.drawable.worker_64,
                getString(R.string.java),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.director),
                getString(R.string.directorDescription),
                R.drawable.structure,
                getString(R.string.none),
                GOLD_COLLAR
            ),
            Vacancy(
                getString(R.string.cleaner),
                getString(R.string.cleanerDescription),
                R.drawable.household,
                getString(R.string.none),
                BLUE_COLLAR
            ),
            Vacancy(
                getString(R.string.guard),
                getString(R.string.guardDescription),
                R.drawable.guard,
                getString(R.string.none),
                BLUE_COLLAR
            ),
            Vacancy(
                getString(R.string.frontend),
                getString(R.string.frontendDescription),
                R.drawable.worker_64,
                getString(R.string.java),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.teamLead),
                getString(R.string.teamLeadDescription),
                R.drawable.worker_64,
                getString(R.string.java),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.guard),
                getString(R.string.guardDescription),
                R.drawable.guard,
                getString(R.string.none),
                BLUE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.backend),
                getString(R.string.backendDescription),
                R.drawable.worker_64,
                getString(R.string.kotlin),
                WHITE_COLLAR
            ),
            Vacancy(
                getString(R.string.cleaner),
                getString(R.string.cleanerDescription),
                R.drawable.household,
                getString(R.string.none),
                BLUE_COLLAR
            ),
        )

        val adapter = RecyclerAdapter(vacancies)
        with(binding) {
            recyclerVacanciesContainer.adapter = adapter
        }
    }

    override fun getTitleRes(): Int = R.string.titleVacancies

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}