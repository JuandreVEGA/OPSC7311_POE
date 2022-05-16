package com.example.group2_part2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivityEditProject : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit_project)

        val addButton: ImageButton = findViewById(R.id.btnAddCat1)

        addButton.setOnClickListener {
            val intent = Intent(this, MainActivityHomePage::class.java)
            startActivity(intent)
        }

        val backButton: Button = findViewById(R.id.btnBack1)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}