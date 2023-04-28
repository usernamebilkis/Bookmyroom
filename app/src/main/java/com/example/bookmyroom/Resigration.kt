package com.example.bookmyroom

import android.content.ContentValues.TAG
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.*
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityResigrationBinding
import com.example.bookmyroom.model.Resigrationdataclass
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.concurrent.TimeUnit

class Resigration : AppCompatActivity() {
    lateinit var binding: ActivityResigrationBinding
    lateinit var auth: FirebaseAuth
    lateinit var number: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResigrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        checkConnectivity()

//        binding.LoginLink.setOnClickListener { startActivity(Intent(this, Login::class.java)) }
//        binding.resgiterbtn.setOnClickListener {
//            var name = binding.resgname.text.toString()
//            var email = binding.resgemail.text.toString()
//            var password = binding.resgpassword.text.toString()
//            var phone = binding.resgphone.text.toString()
//            database = FirebaseDatabase.getInstance().getReference("UserResgiration")
//            val Resigrationdataclass = Resigrationdataclass(name, email, password, phone)
//            database.child(name).setValue(Resigrationdataclass).addOnSuccessListener {
//                binding.resgname.text.clear()
//                binding.resgemail.text.clear()
//                binding.resgpassword.text.toString()
//                binding.resgphone.text.toString()
//                Toast.makeText(this,"Succesfully Add",Toast.LENGTH_LONG).show()
//                startActivity(Intent(this,Login::class.java))
//            }.addOnFailureListener {                  Toast.makeText(this,"Failured to add",Toast.LENGTH_LONG).show()
//            }
//        }

        inits()
        binding.resgiterbtn.setOnClickListener {
            number = binding.resgphone.text.trim().toString()
            if (number.isNotEmpty()) {
                if (number.length == 10) {
                    number = "+91$number"

                    binding.progressBar3.visibility = View.VISIBLE
                binding.resgiterbtn.setBackgroundResource(R.drawable.sucessbtn)
                    binding.resgiterbtn.setTextColor(getResources().getColor(R.color.black))
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                } else {
                    Toast.makeText(this, "Number More Then 10 Digit", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Enter The Number", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun inits() {
        auth= FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this);
        binding.progressBar3.visibility = View.GONE

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
               Toast.makeText(this@Resigration,"AUTHENTICATE SUCCESSFUL",Toast.LENGTH_LONG).show()
                    sendToMain()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential: ${ task.exception.toString()}")
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
                binding.progressBar3.visibility=View.INVISIBLE
            }
    }

     private fun sendToMain(){ startActivity(Intent(this@Resigration,Login::class.java)) }
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            val intent = Intent(this@Resigration, otp::class.java)
            intent.putExtra("OTP", verificationId)
            intent.putExtra("resendToken", token)
            intent.putExtra("phonenumber",number)
            startActivity(intent)
            binding.progressBar3.visibility = View.VISIBLE
        }
    }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            startActivity(Intent(this@Resigration,Login::class.java))
        }
    }

    private fun checkConnectivity() {
        val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = manager.activeNetworkInfo

        if (null == activeNetwork) {
            val dialogBuilder = AlertDialog.Builder(this)
            val intent = Intent(this, MainActivity::class.java)
            // set message of alert dialog
            dialogBuilder.setMessage("Make sure that WI-FI or mobile data is turned on, then try again")
                // if the dialog is cancelable
                .setCancelable(false)
                // positive button text and action
                .setPositiveButton("Retry", DialogInterface.OnClickListener { dialog, id ->
                    recreate()
                })
                // negative button text and action
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, id ->
                 dialog.dismiss()
                })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("No Internet Connection")
            alert.setIcon(R.drawable.baseline_wifi_off_24)
            // show alert dialog
            alert.show()
        }
    }


}

