package com.example.bookmyroom

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.bookmyroom.databinding.ActivitySigninandphoneotpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider

class Signinandphoneotp : AppCompatActivity() {
    lateinit var binding:ActivitySigninandphoneotpBinding
    lateinit var gsp:GoogleSignInOptions
    private var auth: FirebaseAuth=FirebaseAuth.getInstance()
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySigninandphoneotpBinding.inflate(layoutInflater)
        setContentView(binding.root)
binding.callotp.setOnClickListener {
    startActivity(Intent(this,Resigration::class.java))
}
        binding.guest.setOnClickListener {
            Global.guestlogin=1
            startActivity(Intent(this,choosescreen::class.java)) }

        binding.emailsign.setOnClickListener { startActivity(Intent(this,Login::class.java))
        }

        gsp = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        googleSignInClient=GoogleSignIn.getClient(this,gsp)
        binding.googlesign.setOnClickListener {
            googleSignInClient.signOut()
            startActivityForResult(googleSignInClient.signInIntent,13)
             }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

           if (requestCode==13 && resultCode== RESULT_OK){
               val task = GoogleSignIn.getSignedInAccountFromIntent(data)
               try {
            val account = task.getResult(ApiException::class.java)!!
                   Log.w(TAG, "signInWithCredential:"+ account.id)
                firebaseAuthWithGoogle(account.idToken)
               }catch (e:ApiException){
                   Log.w(TAG, "signInWithCredential:failure", task.exception)
               }
           }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {

        val credential=GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task->
            if (task.isSuccessful){
                Log.d(TAG, "signInWithCredential: Success")
                val user =auth.currentUser
                updateUI(user)
                startActivity(Intent(this,newmain::class.java))
                finish()
            }else{
                Log.d(TAG, "signInWithCredential: Failure",task.exception)
//                Snackbar.make(,"Authentication Failed",Snackbar.LENGTH_LONG).show()
                Toast.makeText(this,"Authentication Failed",Toast.LENGTH_LONG).show()
                updateUI(null)
            }

            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()

            }
    }

    private fun updateUI(user: FirebaseUser?) {

    }






}