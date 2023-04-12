package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookmyroom.databinding.ActivityResigrationBinding
import com.example.bookmyroom.model.Resigrationdataclass
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Resigration : AppCompatActivity() {
    lateinit var binding:ActivityResigrationBinding
    private  lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResigrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.LoginLink.setOnClickListener { startActivity(Intent(this,Login::class.java)) }

        binding.resgiterbtn.setOnClickListener {
            var name = binding.resgname.text.toString()
            var email = binding.resgemail.text.toString()
            var password = binding.resgpassword.text.toString()
            var phone = binding.resgphone.text.toString()
            database = FirebaseDatabase.getInstance().getReference("UserResgiration")
            val Resigrationdataclass = Resigrationdataclass(name, email, password, phone)
            database.child(name).setValue(Resigrationdataclass).addOnSuccessListener {
                binding.resgname.text.clear()
                binding.resgemail.text.clear()
                binding.resgpassword.text.toString()
                binding.resgphone.text.toString()

                Toast.makeText(this,"Succesfully Add",Toast.LENGTH_LONG).show()

                startActivity(Intent(this,Login::class.java))
            }.addOnFailureListener {                  Toast.makeText(this,"Failured to add",Toast.LENGTH_LONG).show()
            }
        }


    }
}