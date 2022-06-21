package com.example.group2_part2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

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

        val user = Firebase.auth.uid

        var description: TextView = findViewById(R.id.txtPDescription)
        var progressButton: Button = findViewById(R.id.Progressbutton)

        progressButton.setOnClickListener{
            val intent = Intent(this, MainActivityProgress::class.java)
            startActivity(intent)
        }

        println(user)

        val db = FirebaseFirestore.getInstance()

        db.collection("Projects").get().addOnCompleteListener{
            if (it.isSuccessful){
                for (document in it.result!!){
                    if (document.data.getValue("UserUID")?.toString() == user){
                        description.text = document.data.getValue("Description").toString()
                    }
                }
            }
        }
    }
}