package com.example.bookmyroom

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bookmyroom.Adapter.adapterclient
import com.example.bookmyroom.databinding.ActivityHomeBinding
import com.example.bookmyroom.model.foronlytest
import com.example.bookmyroom.model.homedataclass
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class Home : AppCompatActivity() {
    lateinit var binding:ActivityHomeBinding
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
 lateinit var  datalist:ArrayList<homedataclass>
 private lateinit var adapter:adapterclient
 var databaseReference:DatabaseReference?=null
    var eventListener :ValueEventListener?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout=findViewById(R.id.drwaer)
        toggle= ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navi.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.contach->{
                    startActivity(Intent(this,contactus::class.java))
                    return@setNavigationItemSelectedListener true
                }
                R.id.share->{
                    val sendIntent = Intent()
                    sendIntent.action = Intent.ACTION_SEND
                    sendIntent.putExtra(
                        Intent.EXTRA_TEXT,
                        "Check out the App at: https://play.google.com/store/apps/details?id=$ com.example.bookmyroom"
                    )
                    sendIntent.type = "text/plain"
                    startActivity(sendIntent)
                     return@setNavigationItemSelectedListener true

                }

                else -> { return@setNavigationItemSelectedListener true}
            }
        }

        binding.textView.setOnClickListener { startActivity(Intent(this,recycel::class.java)) }
        if (Global.fabbutton ==1){
            binding.floatingActionButton.visibility=View.VISIBLE
            binding.floatingActionButton.setOnClickListener { startActivity(Intent(this,clientdata::class.java)) }
        } else{
            binding.floatingActionButton.visibility=View.GONE
        }

      binding.recy.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val builder =AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setView(R.layout.loging)
        val dialog = builder.create()
        dialog.show()

        datalist= ArrayList()
        adapter= adapterclient(this,datalist)
        binding.recy.adapter=adapter
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference("Clientdata")
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

        binding.searchView.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchlist(newText)
           return true
            }
        })
    }

    fun searchlist(text:String){
        val searchlist =ArrayList<homedataclass>()
        for (dataclass in datalist){
            if(dataclass.city?.lowercase()?.contains(text.lowercase())==true){
                searchlist.add(dataclass)
            }
        }
        adapter.searchdatalist(searchlist)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
      return true
    }





}