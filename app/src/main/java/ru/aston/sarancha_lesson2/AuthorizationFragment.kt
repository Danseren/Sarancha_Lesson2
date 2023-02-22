package ru.aston.sarancha_lesson2

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.aston.sarancha_lesson2.Utils.CORRECT_PASSWORD
import ru.aston.sarancha_lesson2.Utils.EMPTY_PASSWORD
import ru.aston.sarancha_lesson2.Utils.makeVisible
import ru.aston.sarancha_lesson2.Utils.navigator
import ru.aston.sarancha_lesson2.databinding.FragmentAuthorizationBinding
import java.util.*
import kotlin.concurrent.schedule

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!


    companion object {
        fun newInstance() = AuthorizationFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnEntrance.apply {
                isEnabled = false
                setOnClickListener {
                    when (etPassword.text.toString()) {
                        CORRECT_PASSWORD -> {
                            authorisationProgressBar.makeVisible()
                            Timer().schedule(2000) {
                                navigator().showMainScreen()
                            }

                        }
                    }
                }
            }

            etPassword.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    char: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    btnEntrance.isEnabled = char.toString().trim { it <= ' ' }.isNotEmpty()
                    when (etPassword.text.toString()) {
                        CORRECT_PASSWORD -> TextInputLayoutPassword.error = null
                        EMPTY_PASSWORD -> TextInputLayoutPassword.error = null
                        else -> TextInputLayoutPassword.error = getString(R.string.wrongPasswordMessage)
                    }

                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}