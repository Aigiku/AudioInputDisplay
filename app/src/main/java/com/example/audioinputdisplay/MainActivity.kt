package com.example.audioinputdisplay



import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val SPEECH_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputBtn: Button = findViewById(R.id.input_button)
        val inputText: TextView = findViewById(R.id.input_textview)

        inputBtn.setOnClickListener {
            displaySpeechRecognizer()
        }
    }



    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val inputText: TextView = findViewById(R.id.input_textview)

        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val spokenText: String? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results?.get(0)
                }

            if (spokenText != null) {
                // spokenTextに入力がある場合のみ
                inputText.text = spokenText
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}




