package com.example.bookmyroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookmyroom.databinding.ActivityPaymentBinding

class payment : AppCompatActivity() {
    lateinit var binding:ActivityPaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.textView24.setOnClickListener { 


        }
//         Checkout.preload(this)
    }

//    private fun savePayement(amount:Int){
//        val checkout=Checkout()
//    }


}


