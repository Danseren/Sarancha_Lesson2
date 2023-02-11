package ru.aston.sarancha_lesson2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.aston.sarancha_lesson2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToFirstActivity.setOnClickListener {
            Intent().let { intent ->
                intent.putExtra(SECOND_ACTIVITY_ET_KEY, binding.etMessage.text.toString())
                setResult(RESULT_OK, intent)
            }
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        binding.etMessage.text.toString().also { message ->
            when (message.isNotBlank()) {
                true -> {
                    outState.putString(SECOND_ACTIVITY_ET_KEY, message)
                }
                false -> { Log.d("@@@", "etMessage is empty")}
            }
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        binding.etMessage.setText(savedInstanceState.getString(SECOND_ACTIVITY_ET_KEY, ""))
        super.onRestoreInstanceState(savedInstanceState)
    }
}
