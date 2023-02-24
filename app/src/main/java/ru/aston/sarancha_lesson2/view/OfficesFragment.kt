package ru.aston.sarancha_lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.OfficeAddress
import ru.aston.sarancha_lesson2.R
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

        val dataOfficeAddress = arrayListOf(
            OfficeAddress(
                getString(R.string.cvOfficeFirstHeader),
                getString(R.string.cvOfficeFirstBody),
                getString(R.string.addressFirstOffice)
            ),
            OfficeAddress(
                getString(R.string.cvOfficeSecondHeader),
                getString(R.string.cvOfficeSecondBody),
                getString(R.string.addressSecondOffice)
            ),
            OfficeAddress(
                getString(R.string.cvOfficeThirdHeader),
                getString(R.string.cvOfficeThirdBody),
                getString(R.string.addressThirdOffice)
            )
        )

        with(binding) {

            officeFirst.setOnClickListener {
                navigator().showOfficeInfoScreen(dataOfficeAddress[0])
            }
            officeSecond.setOnClickListener {
                navigator().showOfficeInfoScreen(dataOfficeAddress[1])
            }
            officeThird.setOnClickListener {
                navigator().showOfficeInfoScreen(dataOfficeAddress[2])
            }
        }
    }

    override fun getTitleRes(): Int = R.string.titleOffices

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}