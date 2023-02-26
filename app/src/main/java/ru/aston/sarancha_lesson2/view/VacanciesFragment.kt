package ru.aston.sarancha_lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.*
import ru.aston.sarancha_lesson2.Utils.*
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

        val vacancies = createVacanciesList()
        var vacanciesFilter: MutableList<Vacancy>
        var adapter: RecyclerAdapter

        with(binding) {
            adapter = RecyclerAdapter(vacancies)
            recyclerVacanciesContainer.adapter = adapter
            chipGroupLanguages.setOnCheckedStateChangeListener { chipGroup, _ ->
                vacanciesFilter = vacancies.toMutableList()
                when (chipGroup.checkedChipId) {
                    chipAll.id -> {
                        adapter = RecyclerAdapter(vacanciesFilter)
                        recyclerVacanciesContainer.adapter = adapter
                    }
                    chipKotlin.id -> {
                        adapter = RecyclerAdapter(vacanciesRemove(vacanciesFilter, 0))
                        recyclerVacanciesContainer.adapter = adapter
                    }
                    chipJava.id -> {
                        adapter = RecyclerAdapter(vacanciesRemove(vacanciesFilter, 1))
                        recyclerVacanciesContainer.adapter = adapter
                    }
                    chipPython.id -> {
                        adapter = RecyclerAdapter(vacanciesRemove(vacanciesFilter, 2))
                        recyclerVacanciesContainer.adapter = adapter
                    }
                }
            }
        }
    }

    /**
     * Возвращает список только тех вакансий, в которых есть заданное поле
     * @vacList - редактируемый список
     * @chipId - в зависимости от нажатого чипа сюда передается значение, указывающее какой язык остается
     */
    private fun vacanciesRemove(vacList: MutableList<Vacancy>, chipId: Int): MutableList<Vacancy> {
        when (chipId) {
            KOTLIN -> {
                vacList.removeAll { it.language == getString(R.string.java) }
                vacList.removeAll { it.language == getString(R.string.python) }
                vacList.removeAll { it.language == getString(R.string.none) }
            }
            JAVA -> {
                vacList.removeAll { it.language == getString(R.string.kotlin) }
                vacList.removeAll { it.language == getString(R.string.python) }
                vacList.removeAll { it.language == getString(R.string.none) }
            }
            PYTHON -> {
                vacList.removeAll { it.language == getString(R.string.kotlin) }
                vacList.removeAll { it.language == getString(R.string.java) }
                vacList.removeAll { it.language == getString(R.string.none) }
            }
        }
        return vacList
    }

    /**
     * Возвращает список случайно сгенерированных вакансий
     */
    private fun createVacanciesList(): ArrayList<Vacancy> {
        val vacanciesList = mutableListOf<Vacancy>()
        for (i in 0..20) {
            when ((0..10).random()) {
                0 -> vacanciesList.add(
                    i, Vacancy(
                        getString(R.string.cleaner),
                        getString(R.string.cleanerDescription),
                        R.drawable.household,
                        BLUE_COLLAR,
                        getString(R.string.none)
                    )
                )
                1 -> vacanciesList.add(
                    i, Vacancy(
                        getString(R.string.guard),
                        getString(R.string.guardDescription),
                        R.drawable.guard,
                        BLUE_COLLAR,
                        getString(R.string.none)
                    )
                )
                2 -> vacanciesList.add(i, getGoldCollar())
                else -> vacanciesList.add(i, getWhiteCollar())
            }
        }
        return vacanciesList as ArrayList<Vacancy>
    }

    /**
     * Создает вакансию "Белого воротничка"
     */
    private fun getWhiteCollar(): Vacancy {
        val randomNum = (0..2).random()
        val randomLang = (0..2).random()
        val jobsList = arrayListOf(
            getString(R.string.backend),
            getString(R.string.frontend),
            getString(R.string.teamLead)
        )
        val descriptionList = arrayListOf(
            getString(R.string.backendDescription),
            getString(R.string.frontendDescription),
            getString(R.string.teamLeadDescription)
        )
        val listLanguages = arrayListOf(
            getString(R.string.kotlin),
            getString(R.string.java),
            getString(R.string.python)
        )
        val listLangIm = arrayListOf(
            R.drawable.worker_kotlin_64,
            R.drawable.worker_java_64,
            R.drawable.worker_python_64
        )
        return Vacancy(
            jobsList[randomNum],
            descriptionList[randomNum],
            listLangIm[randomLang],
            WHITE_COLLAR,
            listLanguages[randomLang]
        )
    }

    /**
     * Создает вакансию из руководящего состава
     */
    private fun getGoldCollar(): Vacancy {
        return when ((0..1).random()) {
            0 -> Vacancy(
                getString(R.string.director),
                getString(R.string.directorDescription),
                R.drawable.structure,
                GOLD_COLLAR,
                getString(R.string.none)
            )
            else -> Vacancy(
                getString(R.string.deputyDirector),
                getString(R.string.deputyDirectorDescription),
                R.drawable.structure,
                GOLD_COLLAR,
                getString(R.string.none)
            )
        }
    }

    override fun getTitleRes(): Int = R.string.titleVacancies

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}