package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.databinding.FragmentOfficeInfoBinding

class OfficeInfoFragment : Fragment() {

    private var _binding: FragmentOfficeInfoBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = OfficeInfoFragment()
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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}