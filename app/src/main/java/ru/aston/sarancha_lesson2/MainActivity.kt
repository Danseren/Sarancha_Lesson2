package ru.aston.sarancha_lesson2

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import ru.aston.sarancha_lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val activityResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(SECOND_ACTIVITY_ET_KEY)?.let {
                    if (it.isNotBlank()) binding.tvMessage.text = it
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            when (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                true -> {activityResult.launch(intent)}
                else -> {startActivityForResult(intent, REQUEST_CODE)}
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == REQUEST_CODE) && (resultCode == RESULT_OK)) {
            data?.getStringExtra(SECOND_ACTIVITY_ET_KEY)?.let {
                if (it.isNotBlank()) binding.tvMessage.text = it
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.tvMessage.text.toString().also { message ->
            when (message.isNotBlank()) {
                true -> {
                    outState.putString(SECOND_ACTIVITY_ET_KEY, message)
                }
                false -> {
                    Log.d("@@@", "etMessage is empty")
                }
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.tvMessage.text = savedInstanceState.getString(FIRST_ACTIVITY_TV_KEY, "")
        super.onRestoreInstanceState(savedInstanceState)
    }
}