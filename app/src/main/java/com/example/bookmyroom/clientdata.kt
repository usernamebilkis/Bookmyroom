package com.example.bookmyroom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityClientdataBinding
import com.example.bookmyroom.model.homedataclass
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class clientdata : AppCompatActivity() {


    lateinit var binding: ActivityClientdataBinding
    var imageUri: String? = null
    var uri: Uri? = null

    var imageUriroom: String? = null
    var uriroom: Uri? = null


    var imageUribathroom: String? = null
    var uribathroom: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientdataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityResultLaucher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uri = data!!.data
                binding.imageonbe.setImageURI(uri)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }

        val activityResultLaucherroom = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val dataroom = result.data
                uriroom = dataroom!!.data
                binding.recyclerView.setImageURI(uriroom)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }


        val activityResultLaucherbathroom = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val databathroom = result.data
                uribathroom= databathroom!!.data
                binding.recyclerView2.setImageURI(uribathroom)
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


        binding.imagebathroomn.setOnClickListener {
            val photopickerbathroom = Intent(Intent.ACTION_PICK)
            photopickerbathroom.type ="image/*"
            activityResultLaucherbathroom.launch(photopickerbathroom)
        }

        binding.submitbtn.setOnClickListener {
            savedata()
            roomimagefun()
            bathimage()
        }
    }

    private fun bathimage() {
        val storagereferncebath = FirebaseStorage.getInstance().reference.child("Roombath Image")
            .child(uribathroom!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagereferncebath.putFile(uribathroom!!).addOnSuccessListener { taskSnapshot ->
            val uriTaskbath = taskSnapshot.storage.downloadUrl
            while (!uriTaskbath.isComplete);
            val urlImagebathroom = uriTaskbath.result
            imageUriroom = urlImagebathroom.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun roomimagefun() {
        val storagereferncerroom = FirebaseStorage.getInstance().reference.child("Room Image")
            .child(uriroom!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagereferncerroom.putFile(uriroom!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImageroom = uriTask.result
            imageUriroom = urlImageroom.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun savedata() {
        val storagerefernce = FirebaseStorage.getInstance().reference.child("Building Image")
            .child(uri!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagerefernce.putFile(uri!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageUri = urlImage.toString()
            uploaddata()
            dialog.dismiss()
        }.addOnFailureListener {
            dialog.dismiss()
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
            imageUri,
            hotelname,
            description,
            price,
            city,
            benfit,
            addressofhotel,
            roomsize,
            imageUriroom,
            imageUribathroom
        )
        FirebaseDatabase.getInstance().getReference("Clientdata").child(hotelname)
            .setValue(homedataclass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sucessfull add", Toast.LENGTH_LONG).show()
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()

            }
    }
}