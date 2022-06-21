package com.example.group2_part2

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var auth: FirebaseAuth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.BTNLogin)
        loginButton.setOnClickListener { login() }

        val registerButton: Button = findViewById(R.id.BTNRegister)
        registerButton.setOnClickListener { register() }
        // Testing Changes
    }
    // Login in user and take them to another page
    private fun login() {

        val email: EditText = findViewById(R.id.loginEmail)
        val password: EditText = findViewById(R.id.loginPassword)

        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val intent = Intent(this, MainActivityEditProject::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
    // Take you to register page
    private fun register() {
        val intent = Intent(this, MainActivityRegister::class.java)
        startActivity(intent)
    }

    fun draw(view: View) {}
}
