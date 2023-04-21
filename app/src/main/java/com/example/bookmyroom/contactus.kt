package com.example.bookmyroom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmyroom.databinding.ActivityContactusBinding

class contactus : AppCompatActivity() {
    lateinit var binding: ActivityContactusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactusBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {

            val mUri: Uri = Uri.parse("smsto:+9082458900")
            val mIntent = Intent(Intent.ACTION_SENDTO, mUri)
            mIntent.setPackage("com.whatsapp")
            mIntent.putExtra("chat", true)
            startActivity(mIntent)
        }


        binding.email.setOnClickListener {
            val mailId = "info@bookmyrooms.co.in"
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailId, null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject text here")
            emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<p><b>Some Content</b></p>" +
                    "http://www.google.com" + "<small><p>More content</p></small>"))
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
    }


}
