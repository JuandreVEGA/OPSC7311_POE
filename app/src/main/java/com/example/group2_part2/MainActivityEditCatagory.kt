package com.example.group2_part2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivityEditCatagory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_edit_catagory)

        val registerButton: Button = findViewById(R.id.btnAddNewCat)
        registerButton.setOnClickListener {
            val intent = Intent(this, MainActivityAddCategory::class.java)
            startActivity(intent)
        }
        val backButton: Button = findViewById(R.id.btnBack)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivityHomePage::class.java)
            startActivity(intent)
        }

    }
}