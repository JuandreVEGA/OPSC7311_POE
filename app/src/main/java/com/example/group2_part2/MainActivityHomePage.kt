package com.example.group2_part2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivityHomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home_page)

        val addButton: ImageButton = findViewById(R.id.btnAddCat1)

        addButton.setOnClickListener { add() }

        val backButton: Button = findViewById(R.id.btnBack2)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivityEditProject::class.java)
            startActivity(intent)
        }
    }

    private fun add() {

        val description: TextView = findViewById(R.id.txtDescription)
        val descriptionFinal = description.text.toString().trim()

        val projectName: TextView = findViewById(R.id.txtName)
        val projectNameFinal = projectName.text.toString().trim()

        val location: TextView = findViewById(R.id.txtLocationInfo)
        val locationFinal = location.text.toString().trim()

        val deadline: TextView = findViewById(R.id.txtCatNameInfo)
        val deadlineFinal = deadline.text.toString().trim()

        val roll: TextView = findViewById(R.id.RoleInfo)
        val rollFinal = roll.text.toString().trim()

        val db1 = Firebase.firestore

        val project = hashMapOf(
            "Description" to descriptionFinal,
            "Project Name" to projectNameFinal,
            "Location" to locationFinal,
            "Deadline" to deadlineFinal,
            "Roll" to rollFinal
        )

        db1.collection("Projects")
            .add(project)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        db1.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        val intent = Intent(this, MainActivityEditCatagory::class.java)
        startActivity(intent)
    }
}