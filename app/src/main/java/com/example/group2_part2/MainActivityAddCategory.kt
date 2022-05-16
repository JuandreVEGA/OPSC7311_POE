package com.example.group2_part2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.*

class MainActivityAddCategory : AppCompatActivity() {

    val storage = Firebase.storage

    var storageRef = storage.reference

    val PICK_IMAGE_REQUEST = 71

    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_add_category)

        val backButton: Button = findViewById(R.id.btnBack3)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivityEditCatagory::class.java)
            startActivity(intent)
        }

        val upload: ImageButton = findViewById(R.id.btnAddCat1)

        upload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE_REQUEST
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            filePath = data.data
            if (filePath != null) {
                val ref = storageRef?.child("Images/" + UUID.randomUUID().toString())
                val uploadTask = ref?.putFile(filePath!!)
            }
        }
    }
}