package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.bookmyroom.databinding.ActivityDetailedpageBinding
import com.google.firebase.FirebaseApp

class Detailedpage : AppCompatActivity() {
    lateinit var binding:ActivityDetailedpageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        FirebaseApp.initializeApp(this);
//       binding.detailbuildimage.setImageResource(intent.getIntExtra("imageroom",0))
//       binding.detailroomimage.setImageResource(intent.getIntExtra("imageroomgetdeatile",0))
//       binding.detailbathroom.setImageResource(intent.getIntExtra("imagebathroomgetdeatile",0))
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

//            val oldImageView: String? = intent.getStringExtra("imageroom")
////            image.setImageResource(oldImageView)

            var image = findViewById<ImageView>(R.id.detailbuildimage)
            val Picture = getIntent().getIntExtra("imageroom",0)
            image.setImageResource(Picture)

            binding.detailbuildimage.setImageResource(bundle.getInt("imageroom",0))
            binding.detailbuildimage.setImageResource(bundle.getInt("imageroom",0))
            binding.detailroomimage.setImageResource(bundle.getInt("imageroomgetdeatile",0))
            binding.detailbathroom.setImageResource(bundle.getInt("imagebathroomgetdeatile",0))
        }
        binding.booknow.setOnClickListener {

            if (Global.guestlogin==1){
                Toast.makeText(this,"RESIGRATERE YOUR SEIF FIRST",Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Resigration::class.java))

            }else{
                startActivity(Intent(this, booking::class.java))

            }
        }
    }




    }

