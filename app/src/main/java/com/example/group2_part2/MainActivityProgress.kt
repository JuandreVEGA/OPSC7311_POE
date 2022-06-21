package com.example.group2_part2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivityProgress : AppCompatActivity() {
    var progr = 20
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_progress)

        val increaseButton: Button = findViewById(R.id.button_incr)
        val decreaseButton: Button = findViewById(R.id.button_decr)

        updateProgressBar()

        increaseButton.setOnClickListener {
            if (progr <= 90) {
                progr += 10
                updateProgressBar()
            }
        }

        decreaseButton.setOnClickListener {
            if (progr >= 10) {
                progr -= 10
                updateProgressBar()
            }
        }
    }


    private fun updateProgressBar() {
        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        val progressTextView: TextView = findViewById(R.id.text_view_progress)
        progressBar.progress = progr
        progressTextView.text = "$progr%"
    }
}