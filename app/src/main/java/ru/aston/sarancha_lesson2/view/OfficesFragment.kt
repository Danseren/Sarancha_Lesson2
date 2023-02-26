package ru.aston.sarancha_lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.OfficeAddress
import ru.aston.sarancha_lesson2.R
import ru.aston.sarancha_lesson2.Utils.ABROAD
import ru.aston.sarancha_lesson2.Utils.MOTHERLAND
import ru.aston.sarancha_lesson2.Utils.navigator
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.databinding.FragmentOfficesBinding

class OfficesFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentOfficesBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = OfficesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val offices = initOfficesList()

        val adapter = RecyclerAdapter(offices).apply {
            clickAction = { navigator().showOfficeInfoScreen(offices[itemPos]) }
        }

        with(binding) {
            recyclerOfficesContainer.adapter = adapter
        }
    }

    private fun initOfficesList(): ArrayList<OfficeAddress> {
        return arrayListOf(

            OfficeAddress(
                getString(R.string.russia),
                getString(R.string.moscow),
                R.drawable.russia,
                MOTHERLAND,
                getString(R.string.addressMoscow)
            ),
            OfficeAddress(
                getString(R.string.russia),
                getString(R.string.stPetersburg),
                R.drawable.russia,
                MOTHERLAND,
                getString(R.string.addressStPetersburg)
            ),
            OfficeAddress(
                getString(R.string.russia),
                getString(R.string.rostovOnDon),
                R.drawable.russia,
                MOTHERLAND,
                getString(R.string.addressRostovOnDon)
            ),
            OfficeAddress(
                getString(R.string.russia),
                getString(R.string.kazan),
                R.drawable.russia,
                MOTHERLAND,
                getString(R.string.addressKazan)
            ),
            OfficeAddress(
                getString(R.string.belarus),
                getString(R.string.minsk),
                R.drawable.belarus,
                ABROAD,
                getString(R.string.addressMinsk)
            ),
            OfficeAddress(
                getString(R.string.belarus),
                getString(R.string.vitebsk),
                R.drawable.belarus,
                ABROAD,
                getString(R.string.addressVitebsk)
            ),
            OfficeAddress(
                getString(R.string.belarus),
                getString(R.string.gomel),
                R.drawable.belarus,
                ABROAD,
                getString(R.string.addressGomel)
            ),
            OfficeAddress(
                getString(R.string.belarus),
                getString(R.string.polotsk),
                R.drawable.belarus,
                ABROAD,
                getString(R.string.addressPolotsk)
            ),
            OfficeAddress(
                getString(R.string.kazakhstan),
                getString(R.string.almaty),
                R.drawable.kazakhstan,
                ABROAD,
                getString(R.string.addressAlmaty)
            )
        )
    }

    override fun getTitleRes(): Int = R.string.titleOffices

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}