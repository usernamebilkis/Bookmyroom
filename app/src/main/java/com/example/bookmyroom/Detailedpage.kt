package com.example.bookmyroom

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.convertTo
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.bookmyroom.databinding.ActivityDetailedpageBinding
import com.google.firebase.FirebaseApp


class Detailedpage : AppCompatActivity() {
    var imageUrl =  ""
    var imageurlroom=""

    lateinit var binding: ActivityDetailedpageBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this);

        drawerLayout = findViewById(R.id.detaildrawer)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navi.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.contach -> {
                    startActivity(Intent(this, contactus::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.share -> {
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out the App at: https://play.google.com/store/apps/details?id=$ com.example.bookmyroom")
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }

        val bundle = intent.extras
        if (bundle != null) {
            binding.detailhotelname.text = bundle.getString("hotelname")

            binding.detaildesc.text = bundle.getString("description")
            binding.detailpricesize.text = bundle.getString("price")
            binding.detailcity.text = bundle.getString("city")
            binding.detaildefefit.text = bundle.getString("benefit")
            binding.detailaddress.text = bundle.getString("address")
            binding.detailroomsize.text = bundle.getString("roomsize")
            imageUrl=bundle.getString("imagebuilding")!!
            Glide.with(this).load(bundle.getString("imagebuilding")).into(binding.detailbuildimage)

            imageurlroom=bundle.getString("imageroom")!!
            Glide.with(this).load(bundle.getString("imageroom")).into(binding.detailroomimage)

        }

        binding.booknow.setOnClickListener {
            if (Global.guestlogin == 1) {
                binding.booknow.setBackgroundResource(R.drawable.sucessbtn)
                binding.booknow.setTextColor(getResources().getColor(R.color.black))
                Toast.makeText(this, "RESIGRATERE YOUR SEIF FIRST", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Resigration::class.java))
            } else {
                binding.booknow.setBackgroundResource(R.drawable.sucessbtn)
                binding.booknow.setTextColor(getResources().getColor(R.color.black))
                startActivity(Intent(this, booking::class.java))
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return true
    }


}

