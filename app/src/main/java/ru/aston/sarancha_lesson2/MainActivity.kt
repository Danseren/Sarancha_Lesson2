package ru.aston.sarancha_lesson2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import ru.aston.sarancha_lesson2.Utils.makeGone
import ru.aston.sarancha_lesson2.Utils.makeVisible
import ru.aston.sarancha_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnContacts.setOnClickListener {
                progressStart()
            }
            btnGallery.setOnClickListener {
                progressStart()
            }
            btnVacancies.setOnClickListener {
                progressStart()
                infoView?.apply {
                    setImageSrc(ResourcesCompat.getDrawable(resources, R.drawable.logo_land, null))
                    setBodyText("body text")
                    setHeaderText("header text")
                }
            }
        }
    }

    private fun progressStart() {
        with(binding) {
            tvOffice.makeGone()
            chipGroup.makeGone()
            flow.makeGone()
            progressCircular.makeVisible()
        }
    }
}