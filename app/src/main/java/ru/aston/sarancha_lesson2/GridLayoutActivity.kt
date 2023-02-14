package ru.aston.sarancha_lesson2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.aston.sarancha_lesson2.databinding.ActivityGridLayoutBinding

class GridLayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGridLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGridLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}