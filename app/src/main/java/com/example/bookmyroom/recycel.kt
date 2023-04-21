package com.example.bookmyroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmyroom.Adapter.clcikhere
import com.example.bookmyroom.Adapter.manually
import com.example.bookmyroom.databinding.ActivityRecycelBinding

class recycel : AppCompatActivity() {
    lateinit var  binding:ActivityRecycelBinding
    lateinit var listofdata:ArrayList<manually>
    lateinit var adapter:clcikhere
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecycelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout=findViewById(R.id.drawerofrecy)
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
        listofdata=ArrayList()
        adapter=clcikhere(this,listofdata)
        binding.recyclerView3.adapter=clcikhere(this,listofdata)
        binding.recyclerView3.layoutManager=LinearLayoutManager(this)
        listofdata.add(manually(R.drawable.imageone,"Yogi Executive","Plot No 31/A, Sector 22, MAFCO Rd, opposite MAFCO Market, Sector 22, Vashi, Navi Mumbai, Maharashtra 400703"))
        listofdata.add(manually(R.drawable.imagetwo,"Hotel Palm Beach Pride","Mahesh Building, A-Wing Sector 15, Plot no 37 Near Croma, Palm Beach Road CBD Belapur Navi Mumbai-400-614"))
        listofdata.add(manually(R.drawable.imagethree,"Hotel Corporate","11-Sai Sagar Building, Plot No. 69 Sector-15, CBD, Belapur, CBD Belapur, Navi Mumbai"))
        listofdata.add(manually(R.drawable.imagefour,"Centre Point Navi Mumbai","DC-1 MIDC Industrial Area, Thane- Belapur Road, Turbhe Naka, Vashi, Navi Mumbai"))
        listofdata.add(manually(R.drawable.imagefive,"Yogi Metropolitan Hotel","Plot No. 4, Sector 23, Opp. Sanpada Rly. Stn. Sanpada, Navi Mumbai - 400 706 Maharashtra, Vashi, Navi Mumbai"))
        listofdata.add(manually(R.drawable.imagethree,"Sky Suites By Monarch","11-Sai Sagar Building, Plot No. 69 Sector-15, CBD, Belapur, CBD Belapur, Navi Mumbai"))

        adapter.notifyDataSetChanged()


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return true
    }
}