package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookmyroom.databinding.ActivityChoosescreenBinding

class choosescreen : AppCompatActivity() {
    lateinit var binding: ActivityChoosescreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.villaimage.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    villaspagfe::class.java
                )
            )
        }
        binding.bunglowimage.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    buildeingpage::class.java
                )
            )
        }
        binding.resortimage.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    resortpage::class.java
                )
            )
        }
        binding.hoteimage.setOnClickListener { startActivity(Intent(this, Home::class.java)) }
    }
}