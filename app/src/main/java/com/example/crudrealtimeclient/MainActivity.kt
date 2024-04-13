package com.example.crudrealtimeclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.crudrealtimeclient.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {

            val searchPhone : String = binding.searchPhone.text.toString()
            if  (searchPhone.isNotEmpty()){
                readData(searchPhone)
            }else{
                Toast.makeText(this,"PLease enter the phone number",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(phone: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(phone).get().addOnSuccessListener {

            if (it.exists()){

                val name = it.child("name").value
                val operator = it.child("operator").value
                val location = it.child("location").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchPhone.text.clear()
                binding.readName.text = name.toString()
                binding.readOperator.text = operator.toString()
                binding.readLocation.text = location.toString()
            }else{
                Toast.makeText(this,"Phone number does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}