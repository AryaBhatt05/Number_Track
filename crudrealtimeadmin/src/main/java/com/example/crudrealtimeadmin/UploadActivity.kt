package com.example.crudrealtimeadmin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.saveButton.setOnClickListener {
            val name = binding.uploadName.text.toString()
            val operator = binding.uploadOperator.text.toString()
            val location = binding.uploadLocation.text.toString()
            val phone = binding.uploadPhone.text.toString()

            if (isValidInput(name, operator, location, phone)) {
                val user = User(name, operator, location, phone)
                database.child(phone).setValue(user)
                    .addOnSuccessListener {
                        // Clear input fields after successful upload
                        binding.uploadName.text.clear()
                        binding.uploadOperator.text.clear()
                        binding.uploadLocation.text.clear()
                        binding.uploadPhone.text.clear()
                        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@UploadActivity, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isValidInput(name: String, operator: String, location: String, phone: String): Boolean {
        return name.isNotBlank() && operator.isNotBlank() && location.isNotBlank() && phone.length == 10 && phone.all { it.isDigit() }
    }
}
