package com.example.bookmyroom

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
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

        binding.textView24.setOnClickListener { savePayement(binding.editTextNumber.text.toString().trim().toInt()) }
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

        var infalte = LayoutInflater.from(this).inflate(R.layout.activity_booking,null)
        var  name = infalte.findViewById<EditText>(R.id.namebooking).toString()
        var  phone = infalte.findViewById<EditText>(R.id.bookingphone).toString()
        var  indate = infalte.findViewById<EditText>(R.id.bookingchecking).toString()
        var  outdate = infalte.findViewById<EditText>(R.id.bookingcheckout).toString()
        var dataans =  name + indate + outdate

        try {
            val smsManager: SmsManager
            if (Build.VERSION.SDK_INT>=23) {
                smsManager = this.getSystemService(SmsManager::class.java)
            } else{ smsManager = SmsManager.getDefault() }
            smsManager.sendTextMessage(phone, null, dataans, null, null)
            Toast.makeText(applicationContext, "Message Sent SMS ", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        binding.textView26.text = p1
        binding.textView26.setTextColor(Color.RED)
    }


}


