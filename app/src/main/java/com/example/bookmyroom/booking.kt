package com.example.bookmyroom

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.bookmyroom.databinding.ActivityBookingBinding
import com.example.bookmyroom.model.Resigrationdataclass
import com.example.bookmyroom.model.bookingsubmit
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class booking : AppCompatActivity() {
    lateinit var binding: ActivityBookingBinding
    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submit.setOnClickListener {
            var namebooking = binding.namebooking.text.toString()
            var phonebooking = binding.bookingphone.text.toString()
            var roomcount = binding.bookingroom.text.toString()
            var checkin = binding.bookingchecking.text.toString()
            var checkout = binding.bookingcheckout.text.toString()
            var child = binding.bookingchild.text.toString()
            var aulyt = binding.bookingadult.text.toString()

            database = FirebaseDatabase.getInstance().getReference("bookingdata")
            val bookingsubmit =
                bookingsubmit(namebooking, phonebooking, roomcount, checkin, checkout, child, aulyt)

            database.child(namebooking).setValue(bookingsubmit).addOnSuccessListener {
                binding.namebooking.text.clear()
                binding.bookingphone.text.clear()
                binding.bookingroom.text.clear()
                binding.bookingchecking.text.clear()
                binding.bookingcheckout.text.clear()
                binding.bookingchild.text.clear()
                binding.bookingadult.text.clear()
                Toast.makeText(this, "Succesfully Add", Toast.LENGTH_LONG).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Failured to add", Toast.LENGTH_LONG).show()

            }
        }
        val mucalendar = Calendar.getInstance()
        val datepicker = DatePickerDialog.OnDateSetListener{
            view,year,month,dayofmonth ->
            mucalendar.set(Calendar.YEAR,year)
            mucalendar.set(Calendar.MONTH,month)
            mucalendar.set(Calendar.DAY_OF_YEAR,dayofmonth)
         updatelable(mucalendar)
        }

        val mucalendarsecond = Calendar.getInstance()
        val datepickersecond = DatePickerDialog.OnDateSetListener{
                view,year,month,dayofmonth ->
            mucalendar.set(Calendar.YEAR,year)
            mucalendar.set(Calendar.MONTH,month)
            mucalendar.set(Calendar.DAY_OF_YEAR,dayofmonth)
            updatelablesecond(mucalendarsecond)
        }

        binding.checkiinimage.setOnClickListener {
            DatePickerDialog(this,datepicker,mucalendar.get(Calendar.YEAR),mucalendar.get(Calendar.MONTH),mucalendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.checkoutimage.setOnClickListener {
            DatePickerDialog(this,datepickersecond,mucalendarsecond.get(Calendar.YEAR),mucalendarsecond.get(Calendar.MONTH),mucalendarsecond.get(Calendar.DAY_OF_MONTH)).show()
        }




    }

    private fun updatelablesecond(mucalendarsecond: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdfff = SimpleDateFormat(myFormat, Locale.UK)
        binding.bookingcheckout.setText(sdfff.format(mucalendarsecond.time))
    }

    private fun updatelable(mucalendar: Calendar) {
val myFormat = "dd-MM-yyyy"
        val sdff = SimpleDateFormat(myFormat, Locale.UK)
        binding.bookingchecking.setText(sdff.format(mucalendar.time))
    }
}