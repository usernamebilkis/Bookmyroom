package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bookmyroom.Adapter.buildingadapter
import com.example.bookmyroom.Adapter.villasadapter
import com.example.bookmyroom.databinding.ActivityBuildeingpageBinding
import com.example.bookmyroom.model.homedataclass
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class buildeingpage : AppCompatActivity() {
    lateinit var binding:ActivityBuildeingpageBinding
    lateinit var  datalist:ArrayList<homedataclass>
    private lateinit var adapter: buildingadapter

    var databaseReference: DatabaseReference?=null
    var eventListener : ValueEventListener?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBuildeingpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        if (Global.fabbutton ==1){
            binding.floatingActionButton2.visibility= View.GONE
        }else{
            binding.floatingActionButton2.visibility= View.VISIBLE
            binding.floatingActionButton2.setOnClickListener { startActivity(Intent(this,bewlogousclientdata::class.java)) }
        }

        binding.buildingrecy.layoutManager= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)


        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()


        datalist= ArrayList()
        adapter= buildingadapter(this,datalist)
        binding.buildingrecy.adapter=adapter
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("ClientdataBewlogus")
        dialog.show()
        eventListener= databaseReference!!.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                datalist.clear()
                for (itemSnapshot in snapshot.children){
                    val dataclass=itemSnapshot.getValue(homedataclass::class.java)
                    if (dataclass != null){
                        datalist.add(dataclass)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })
        fun searchlist(text:String){
            val searchlist =ArrayList<homedataclass>()
            for (dataclass in datalist){
                if(dataclass.city?.lowercase()?.contains(text.lowercase())==true){
                    searchlist.add(dataclass)
                }
            }
            adapter.searchdatalist(searchlist)
        }

        binding.searchView3.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchlist(newText)
                return true
            }
        })

        binding.textView.setOnClickListener { startActivity(Intent(this,recycel::class.java)) }
    }
    }
