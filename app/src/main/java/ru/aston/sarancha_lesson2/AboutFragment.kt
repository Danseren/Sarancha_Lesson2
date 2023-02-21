package ru.aston.sarancha_lesson2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.contract.HasCustomTitle
import ru.aston.sarancha_lesson2.databinding.FragmentAboutBinding

class AboutFragment : Fragment(), HasCustomTitle {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

        }
    }

    override fun getTitleRes(): Int = R.string.titleAbout

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}