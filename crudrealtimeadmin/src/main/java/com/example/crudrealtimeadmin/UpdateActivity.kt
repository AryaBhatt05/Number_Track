package com.example.crudrealtimeadmin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.updateButton.setOnClickListener {
            val referencePhone = binding.referencePhone.text.toString().trim()
            val updateName = binding.updateName.text.toString().trim()
            val updateOperator = binding.updateOperator.text.toString().trim()
            val updateLocation = binding.updateLocation.text.toString().trim()

            if (isValidInput(referencePhone, updateName, updateOperator, updateLocation)) {
                updateData(referencePhone, updateName, updateOperator, updateLocation)
            }
        }
    }

    private fun isValidInput(
        phone: String,
        name: String,
        operator: String,
        location: String
    ): Boolean {
        return if (phone.length != 10) {
            Toast.makeText(this, "Phone number must be 10 digits", Toast.LENGTH_SHORT).show()
            false
        } else if (name.isEmpty() || operator.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun updateData(phone: String, name: String, operator: String, location: String) {
        val user = mapOf(
            "name" to name,
            "operator" to operator,
            "location" to location
        )

        database.child(phone).updateChildren(user).addOnSuccessListener {
            binding.referencePhone.text.clear()
            binding.updateName.text.clear()
            binding.updateOperator.text.clear()
            binding.updateLocation.text.clear()
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }
}