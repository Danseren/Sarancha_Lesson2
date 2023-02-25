package ru.aston.sarancha_lesson2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.OfficeAddress
import ru.aston.sarancha_lesson2.R
import ru.aston.sarancha_lesson2.Utils.ARG_OPTIONS
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.databinding.FragmentOfficeInfoBinding

class OfficeInfoFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentOfficeInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var officeAddress: OfficeAddress

    companion object {
        fun newInstance(officeAddress: OfficeAddress): OfficeInfoFragment {
            val args = Bundle()
            args.putParcelable(ARG_OPTIONS, officeAddress)
            val fragment = OfficeInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        officeAddress = arguments?.getParcelable(ARG_OPTIONS)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOfficeInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            tvCountry.text = officeAddress.bodyText
            tvCity.text = officeAddress.bodyText
            tvAddress.text = officeAddress.address
            ivFlag.setImageResource(officeAddress.imageSrc)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(ARG_OPTIONS, officeAddress)
    }

    override fun getTitleRes(): Int = R.string.titleOffices

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}