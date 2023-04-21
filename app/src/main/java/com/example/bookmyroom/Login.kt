package com.example.bookmyroom

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityLoginBinding
import com.example.bookmyroom.model.Logindataclass
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    lateinit var  binding: ActivityLoginBinding
    private  lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        FirebaseApp.initializeApp(this);
        checkConnectivity()
        binding.textView6.setOnClickListener {
            val email = binding.loginemail.text.toString()
            val passwod = binding.passwordemail.text.toString()
                     if (email.isEmpty()&& passwod.isEmpty()){
                         Toast.makeText(this,"Enter Email and Password", Toast.LENGTH_LONG).show()
                     }else{
                         if (email.equals("admin123@gmail.com") || passwod.equals("admin") ){
                             Global.fabbutton =1
                             binding.textView6.setBackgroundResource(R.drawable.sucessbtn)
                             binding.textView6.setTextColor(getResources().getColor(R.color.black))
                             startActivity(Intent(this,choosescreen::class.java))
                         }
                         else
                         {

                             binding.textView6.setBackgroundResource(R.drawable.sucessbtn)
                             binding.textView6.setTextColor(getResources().getColor(R.color.black))
                             database = FirebaseDatabase.getInstance().getReference("UserLogin")
                             val Logindataclass = Logindataclass(email,passwod)
                             database.child(passwod).setValue(Logindataclass).addOnSuccessListener {
                                 binding.loginemail.text.clear()
                                 binding.passwordemail.text.clear()


                                 Toast.makeText(this,"Succesfully Add", Toast.LENGTH_LONG).show()
                                 startActivity(Intent(this,choosescreen::class.java))
                             }.addOnFailureListener {Toast.makeText(this,"Failured to add",Toast.LENGTH_LONG).show()
                             }
                         }
                     }




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