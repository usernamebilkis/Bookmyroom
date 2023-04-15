package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookmyroom.databinding.ActivityLoginBinding
import com.example.bookmyroom.model.Logindataclass
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    lateinit var  binding: ActivityLoginBinding
    private  lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        FirebaseApp.initializeApp(this);
//        binding.loginbtn.setOnClickListener {
//
//        }

        binding.loginbtn.setOnClickListener {
            var email = binding.loginemail.text.toString()
            var passwod = binding.passwordemail.text.toString()
            if (   email.equals("admin123@gmail.com") || passwod.equals("admin")){
                Global.fabbutton =1
                startActivity(Intent(this,choosescreen::class.java))
            }else{
                database = FirebaseDatabase.getInstance().getReference("UserLogin")
                val Logindataclass = Logindataclass(email,passwod)
                database.child(passwod).setValue(Logindataclass).addOnSuccessListener {
                    binding.loginemail.text.clear()
                    binding.passwordemail.text.clear()
                    Toast.makeText(this,"Succesfully Add", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,choosescreen::class.java))
                }.addOnFailureListener {Toast.makeText(this,"Failured to add",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}