package com.example.bookmyroom

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityPaymentBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class payment : AppCompatActivity(), PaymentResultListener {
    lateinit var binding: ActivityPaymentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.textView24.setOnClickListener {
            savePayement(binding.editTextNumber.text.toString().trim().toInt())
        }
        Checkout.preload(this)
    }

    private fun savePayement(amount: Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_live_Iv44xDuFQgQLyk")
        try {
            val option = JSONObject()
            option.put("name", "Bookmyrooms")
            option.put("description", "Pay your Room Booking bill")
            option.put("theme.color", "#DC1A1A")
            option.put("currency", "INR")
            option.put("amount", amount * 100)
            val retryobj = JSONObject()
            retryobj.put("enabled", true)
            retryobj.put("max_count", 4)
            option.put("retry", retryobj)
            checkout.open(this, option)
        } catch (e: java.lang.Exception) {
            Toast.makeText(this, "Error in payment : " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    override fun onPaymentSuccess(p0: String?) {
        binding.textView26.text = p0
        binding.textView26.setTextColor(Color.GREEN)

        binding.textView24.visibility = View.GONE
        binding.editTextNumber.visibility = View.GONE
        binding.imageView32.visibility = View.GONE
        binding.imageView33.visibility = View.GONE
        binding.imageView35.visibility = View.VISIBLE


    }

    override fun onPaymentError(p0: Int, p1: String?) {
        binding.textView26.text = p1
        binding.textView26.setTextColor(Color.RED)
    }


}


