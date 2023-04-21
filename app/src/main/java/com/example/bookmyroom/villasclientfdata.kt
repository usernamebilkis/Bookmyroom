package com.example.bookmyroom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityVillasclientfdataBinding
import com.example.bookmyroom.model.homedataclass
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class villasclientfdata : AppCompatActivity() {
    lateinit var binding:ActivityVillasclientfdataBinding
        var imageUriVillas:String? = null
    var uriVillas: Uri? = null

    var imageUriroomVillas: String? = null
    var uriroomVillas: Uri? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVillasclientfdataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val activityResultLaucher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uriVillas = data!!.data
                binding.imageonbe.setImageURI(uriVillas)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }

        val activityResultLaucherroom = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val dataroom = result.data
                uriroomVillas = dataroom!!.data
                binding.recyclerView.setImageURI(uriroomVillas)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }




        binding.buildcamera.setOnClickListener {
            val photopicker = Intent(Intent.ACTION_PICK)
            photopicker.type = "image/*"
            activityResultLaucher.launch(photopicker)
        }

        binding.imageroom.setOnClickListener {
            val photopickerroom = Intent(Intent.ACTION_PICK)
            photopickerroom.type ="image/*"
            activityResultLaucherroom.launch(photopickerroom)
        }



        binding.submitbtn.setOnClickListener {
            savedata()
            roomimagefun()
        }
    }



    private fun uploaddata() {
        var hotelname = binding.hotelnameclient.text.toString()
        var price = binding.petrnight.text.toString()
        var city = binding.city.text.toString()
        var benfit = binding.benefittext.text.toString()
        var addressofhotel = binding.addressofhotel.text.toString()
        var description = binding.Desctxt.text.toString()
        var roomsize = binding.roomsize.text.toString()


        var homedataclass = homedataclass(
            imageUriVillas,
            hotelname,
            description,
            price,
            city,
            benfit,
            addressofhotel,
            roomsize,
            imageUriroomVillas,

        )
        FirebaseDatabase.getInstance().getReference("Clientdatavillas").child(hotelname)
            .setValue(homedataclass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sucessfull add", Toast.LENGTH_LONG).show()
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()

            }
    }

    private fun roomimagefun() {
        val storagereferncerroom = FirebaseStorage.getInstance().reference.child("Roomvillas Image")
            .child(uriroomVillas!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagereferncerroom.putFile(uriroomVillas!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImageroom = uriTask.result
            imageUriroomVillas = urlImageroom.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun savedata() {
        val storagerefernce = FirebaseStorage.getInstance().reference.child("Villafornt Image")
            .child(uriVillas!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagerefernce.putFile(uriVillas!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageUriVillas = urlImage.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }
}