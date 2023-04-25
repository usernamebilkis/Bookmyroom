package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        binding.textView33.setOnClickListener {
            if (Global.guestlogin == 1) {
                binding.textView33.setBackgroundResource(R.drawable.sucessbtn)
                binding.textView33.setTextColor(getResources().getColor(R.color.black))
                Toast.makeText(this, "RESIGRATERE YOUR SEIF FIRST", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Resigration::class.java))
            } else {
                binding.textView33.setBackgroundResource(R.drawable.sucessbtn)
                binding.textView33.setTextColor(getResources().getColor(R.color.black))
                startActivity(Intent(this, booking::class.java))
            }
        }
    }
}