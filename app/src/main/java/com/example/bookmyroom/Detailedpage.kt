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
import com.example.bookmyroom.databinding.ActivityDetailedpageBinding
import com.google.firebase.FirebaseApp


class Detailedpage : AppCompatActivity() {
    lateinit var binding:ActivityDetailedpageBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedpageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FirebaseApp.initializeApp(this);

        drawerLayout=findViewById(R.id.detaildrawer)
        toggle= ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navi.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.contach->{
                    startActivity(Intent(this,contactus::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.share->{
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Check out the App at: https://play.google.com/store/apps/details?id=$ com.example.bookmyroom"
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                    return@setNavigationItemSelectedListener true

                }
                else -> { return@setNavigationItemSelectedListener true}
            }
        }

        val bundle = intent.extras
        if (bundle != null){
            binding.detailhotelname.text = intent.getStringExtra("hotelname")
        binding.detaildesc.text = intent.getStringExtra("description")
        binding.detailpricesize.text = intent.getStringExtra("price")
        binding.detailcity.text = intent.getStringExtra("city")
        binding.detaildefefit.text = intent.getStringExtra("benefit")
        binding.detailaddress.text = intent.getStringExtra("address")
        binding.detailroomsize.text = intent.getStringExtra("roomsize")

              var data= bundle.getInt("imagebuilding",0)
            val imageView = findViewById<ImageView>(R.id.detailbuildimage)
           imageView.setImageResource(data)

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true }
        return true
    }


    }

