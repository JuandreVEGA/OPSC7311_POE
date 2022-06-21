package com.example.group2_part2

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivityRegister : AppCompatActivity() {

    var auth: FirebaseAuth = Firebase.auth

    var uid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        auth = FirebaseAuth.getInstance()

        val backButton: Button = findViewById(R.id.BTNback)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        var currentUser = auth!!.currentUser

        if (currentUser != null) {
            Log.d(ContentValues.TAG, currentUser.displayName.toString())
        }

        val regButton: Button = findViewById(R.id.BTNregister)
        regButton.setOnClickListener { reg() }
    }

    // UpdateUI in Firebase

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            Log.d(ContentValues.TAG, currentUser.displayName.toString())
            val intent = Intent(this, MainActivityEditProject::class.java)
            intent.putExtra("user", currentUser.displayName.toString())
            startActivity(intent)
        }
    }

    // Create your account and store it in Firebase

    private fun createAccount(email: String, password: String, name: String, companyName: String,) {
        auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this)

        { task ->
            if (task.isSuccessful) {
                println(task.getResult().getUser())

                uid = task.getResult().getUser()?.getUid().toString();

                val db = Firebase.firestore

                val user = hashMapOf(
                    "Email" to email,
                    "Name" to name,
                    "CompanyName" to companyName,
                    "Password" to password,
                    "UserUid" to uid
                )

                db.collection("Users").document(uid)
                    .set(user)
                    .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                    .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

                db.collection("users")
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d(TAG, "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }
                updateUI(task.getResult().getUser())
            } else {
                updateUI(null)
            }
        }
    }

    private fun reg() {

        val password1: TextView = findViewById(R.id.password1)
        val password2: TextView = findViewById(R.id.password2)

       val companyName: TextView = findViewById(R.id.companyName)
       val email: TextView = findViewById(R.id.email)
       val name: TextView = findViewById(R.id.name)

        val passwordFinal1 = password1.getText().toString().trim()
        val passwordFinal2 = password2.getText().toString().trim()
        val companyNameFinal = companyName.getText().toString().trim()
        val emailFinal = email.getText().toString().trim()
        val nameFinal = name.getText().toString().trim()

        // Checks to see if fields are empty and runs createAccount method

        if (!nameFinal.isNullOrEmpty() || !passwordFinal1.isNullOrEmpty() || !passwordFinal2.isNullOrEmpty() || !companyNameFinal.isNullOrEmpty() || !emailFinal.isNullOrEmpty()) {

            if (passwordFinal1 == passwordFinal2) {
                println("Password does match")
                // creating account
                createAccount(emailFinal, passwordFinal1, nameFinal, companyNameFinal)
            } else {
                println("Password does not match")
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Alert")
                builder.setMessage("Passwords do not match ! ")
                builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                    Toast.makeText(
                        applicationContext,
                        android.R.string.yes, Toast.LENGTH_SHORT
                    ).show()
                }
                builder.show()
            }
        } else {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Alert")
            builder.setMessage("Please Make Sure All Fields Are Filled in !")
            builder.setPositiveButton(android.R.string.yes)
            { dialog, which ->
                Toast.makeText(
                    applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT
                ).show()
            }
            builder.show()
        }
    }
}