package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bookmyroom.databinding.ActivityDetailedpageBinding

class Detailedpage : AppCompatActivity() {
    lateinit var binding:ActivityDetailedpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

//       binding.detailbuildimage.setImageResource(intent.getIntExtra("imageroom",0))
//       binding.detailroomimage.setImageResource(intent.getIntExtra("imageroomgetdeatile",0))
//       binding.detailbathroom.setImageResource(intent.getIntExtra("imagebathroomgetdeatile",0))
//
//        binding.detailhotelname.text = intent.getStringExtra("hotelname")
//        binding.detaildesc.text = intent.getStringExtra("description")
//        binding.detailpricesize.text = intent.getStringExtra("price")
//        binding.detailcity.text = intent.getStringExtra("city")
//        binding.detaildefefit.text = intent.getStringExtra("benefit")
//        binding.detailaddress.text = intent.getStringExtra("address")
//        binding.detailroomsize.text = intent.getStringExtra("roomsize")

        val bundle = intent.extras
        if (bundle != null){
            binding.detailhotelname.text= bundle.getString("hotelname")
            binding.detaildesc.text=bundle.getString("description")
            binding.detailpricesize.text=bundle.getString("price")
            binding.detailcity.text=bundle.getString("city")
            binding.detaildefefit.text=bundle.getString("benefit")
            binding.detailaddress.text=bundle.getString("address")
            binding.detailroomsize.text=bundle.getString("roomsize")
            binding.detailbuildimage.setImageResource(bundle.getInt("imageroom"))
            binding.detailroomimage.setImageResource(bundle.getInt("imageroomgetdeatile"))
            binding.detailbathroom.setImageResource(bundle.getInt("imagebathroomgetdeatile"))
        }
        binding.booknow.setOnClickListener {
            startActivity(Intent(this, booking::class.java))
        }
    }




    }
