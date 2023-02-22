package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

        with(binding) {

            tv1.setOnClickListener {
                navigator().showOfficeInfoScreen()
            }
        }
    }

    override fun getTitleRes(): Int = R.string.titleOffices

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}