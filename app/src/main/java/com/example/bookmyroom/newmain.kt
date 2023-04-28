package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookmyroom.databinding.ActivityNewmainBinding

class newmain : AppCompatActivity() {
    lateinit var binding:ActivityNewmainBinding
    var tabtitledata = arrayOf("Hotel","Villas","Bungalow","Resort")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener{
            when(it.itemId){
                R.id.explore->{
                    startActivity(Intent(this,Home::class.java))
                    return@setOnItemSelectedListener true
                }
                R.id.wishlite->{
                    startActivity(Intent(this,heartpage::class.java))
                    return@setOnItemSelectedListener true
                }

                else -> {
                    startActivity(Intent(this,details::class.java))
                    return@setOnItemSelectedListener true
                }
            }
        }




    }
}