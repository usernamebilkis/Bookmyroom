package com.example.bookmyroom

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.bookmyroom.databinding.ActivityBewlogousclientdataBinding
import com.example.bookmyroom.model.homedataclass
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class bewlogousclientdata : AppCompatActivity() {
    lateinit var binding:ActivityBewlogousclientdataBinding
    var imageUribewlogus: String? = null
    var uribewlogus: Uri? = null

    var imageUriroombewlogus: String? = null
    var uriroombewlogus: Uri? = null

    var imageUribathroombewlogus: String? = null
    var uribathroombewlogus: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBewlogousclientdataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val activityResultLaucher = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data
                uribewlogus = data!!.data
                binding.imageonbe.setImageURI(uribewlogus)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }

        val activityResultLaucherroom = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val dataroom = result.data
                uriroombewlogus = dataroom!!.data
                binding.recyclerView.setImageURI(uriroombewlogus)
            } else {
                Toast.makeText(this, "No Image selected", Toast.LENGTH_LONG).show()
            }

        }


        val activityResultLaucherbathroom = registerForActivityResult<Intent, ActivityResult>(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val databathroom = result.data
                uribathroombewlogus= databathroom!!.data
                binding.recyclerView2.setImageURI(uribathroombewlogus)
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

    private fun savedata() {
        val storagerefernce = FirebaseStorage.getInstance().reference.child("BewlogusBuilding Image")
            .child(uribewlogus!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagerefernce.putFile(uribewlogus!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImage = uriTask.result
            imageUribewlogus = urlImage.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }

    private fun roomimagefun() {
        val storagereferncerroom = FirebaseStorage.getInstance().reference.child("BewlogusRoom Image")
            .child(uriroombewlogus!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagereferncerroom.putFile(uriroombewlogus!!).addOnSuccessListener { taskSnapshot ->
            val uriTask = taskSnapshot.storage.downloadUrl
            while (!uriTask.isComplete);
            val urlImageroom = uriTask.result
            imageUriroombewlogus = urlImageroom.toString()
            uploaddata()

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
            imageUribewlogus,
            hotelname,
            description,
            price,
            city,
            benfit,
            addressofhotel,
            roomsize,
            imageUriroombewlogus,
            imageUribathroombewlogus
        )
        FirebaseDatabase.getInstance().getReference("ClientdataBewlogus").child(hotelname)
            .setValue(homedataclass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Sucessfull add", Toast.LENGTH_LONG).show()
                    finish()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()

            }
    }

    private fun bathimage() {
        val storagereferncebath = FirebaseStorage.getInstance().reference.child("BewlogusRoombath Image")
            .child(uribathroombewlogus!!.lastPathSegment!!)
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        storagereferncebath.putFile(uribathroombewlogus!!).addOnSuccessListener { taskSnapshot ->
            val uriTaskbath = taskSnapshot.storage.downloadUrl
            while (!uriTaskbath.isComplete);
            val urlImagebathroom = uriTaskbath.result
            imageUribathroombewlogus = urlImagebathroom.toString()
            uploaddata()

        }.addOnFailureListener {
            dialog.dismiss()
        }
    }
}