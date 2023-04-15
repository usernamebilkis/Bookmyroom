package com.example.bookmyroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookmyroom.databinding.ActivityClicktheredetailsBinding

class Clicktheredetails : AppCompatActivity() {
    lateinit var binding:ActivityClicktheredetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityClicktheredetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val bundle = intent.extras
        if (bundle != null){
            binding.imagephotodetails.setImageResource(bundle.getInt("hotelimage"))
            binding.detailhotelnamerecydetail.text=bundle.getString("hotelname")
            binding.hoteladdress.text=bundle.getString("hoteititle")

        }
    }
}