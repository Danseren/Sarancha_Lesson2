package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.aston.sarancha_lesson2.databinding.FragmentThirdBinding

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var binding: FragmentThirdBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentThirdBinding.bind(view)

        binding.btnThirdToFirst.setOnClickListener {
            findNavController().popBackStack(R.id.firstFragment, false)
        }

        binding.btnThirdToSecond.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}