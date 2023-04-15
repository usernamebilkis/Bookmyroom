package com.example.bookmyroom

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.bookmyroom.databinding.ActivityOtpBinding
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class otp : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var otp: String
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var phonenumber: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        otp = intent.getStringExtra("OTP").toString()
        resendToken = intent.getParcelableExtra("resendToken")!!
        phonenumber = intent.getStringExtra("phonenumber")!!

        inits()
        binding.progressBar2.visibility = View.INVISIBLE
        addTextChangeListener()
        resendOtpVisisbility()
        binding.resendtext.setOnClickListener {
            resendverifcatedcode()
            resendOtpVisisbility()
        }


        binding.verfitybtn.setOnClickListener {
            var typeotp =
                (binding.otpone.text.toString() + binding.otptwo.text.toString() + binding.otpthree.text.toString() + binding.otpfour.text.toString() + binding.otpfive.text.toString() + binding.otpsix.text.toString())

            if (typeotp.isNotEmpty()) {
                if (typeotp.length == 6) {
                    val credential: PhoneAuthCredential =
                        PhoneAuthProvider.getCredential(otp, typeotp)
                    binding.progressBar2.visibility = View.VISIBLE
                    signInWithPhoneAuthCredential(credential)

                } else {
                    Toast.makeText(this@otp, "Please Enter Correct Opt", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(this@otp, "Please Enter Opt", Toast.LENGTH_LONG).show()
            }

        }


    }

    fun resendOtpVisisbility() {
        binding.otpone.setText("")
        binding.otptwo.setText("")
        binding.otpthree.setText("")
        binding.otpfour.setText("")
        binding.otpfive.setText("")
        binding.otpsix.setText("")
        binding.resendtext.visibility = View.INVISIBLE
        binding.resendtext.isEnabled = false
        Handler(Looper.myLooper()!!).postDelayed(Runnable {
            binding.resendtext.visibility = View.VISIBLE
            binding.resendtext.isEnabled = true
        }, 60000)
    }

    private fun resendverifcatedcode() {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phonenumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this)                 // Activity (for callback binding)
            .setCallbacks(callbacks)
            .setForceResendingToken(resendToken)// OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.

            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.


            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Log.d("TAG", "onVerificationFailed: ${e.toString()}")
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            otp = verificationId
            resendToken = token

            // Save verification ID and resending token so we can use them later

//            val intent = Intent(this@otp, com.example.bookmyroom.otp::class.java)
//            intent.putExtra("OTP", verificationId)
//            intent.putExtra("resendToken", token)
//            intent.putExtra("phonenumber", phonenumber)
//            startActivity(intent)
//            binding.progressBar2.visibility = View.VISIBLE

        }


    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    binding.progressBar2.visibility = View.VISIBLE
                    Toast.makeText(this@otp, "AUTHENTICATE SUCCESSFUL", Toast.LENGTH_LONG).show()
                    sendToMain()

                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(ContentValues.TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid

                    }

                    binding.progressBar2.visibility=View.VISIBLE
                    // Update UI
                }
            }
    }

    private fun sendToMain() {
        startActivity(Intent(this@otp, Login::class.java))
    }

    private fun addTextChangeListener() {
        binding.otpone.addTextChangedListener(EditTextWatcher(binding.otpone))
        binding.otptwo.addTextChangedListener(EditTextWatcher(binding.otptwo))
        binding.otpthree.addTextChangedListener(EditTextWatcher(binding.otpthree))
        binding.otpfour.addTextChangedListener(EditTextWatcher(binding.otpfour))
        binding.otpfive.addTextChangedListener(EditTextWatcher(binding.otpfive))
        binding.otpsix.addTextChangedListener(EditTextWatcher(binding.otpsix))
    }

    private fun inits() {
        auth = FirebaseAuth.getInstance()
    }

    inner class EditTextWatcher(private val view: View) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun afterTextChanged(p0: Editable?) {
            val text = p0.toString()
            when (view.id) {
                R.id.otpone -> if (text.length == 1) binding.otptwo.requestFocus()
                R.id.otptwo -> if (text.length == 1) binding.otpthree.requestFocus() else if (text.isEmpty()) binding.otpone.requestFocus()
                R.id.otpthree -> if (text.length == 1) binding.otpfour.requestFocus() else if (text.isEmpty()) binding.otptwo.requestFocus()
                R.id.otpfour -> if (text.length == 1) binding.otpfive.requestFocus() else if (text.isEmpty()) binding.otpthree.requestFocus()
                R.id.otpfive -> if (text.length == 1) binding.otpsix.requestFocus() else if (text.isEmpty()) binding.otpfour.requestFocus()
                R.id.otpsix -> if (text.isEmpty()) binding.otpfive.requestFocus()
            }
        }

    }
}