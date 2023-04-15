package com.example.bookmyroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookmyroom.Adapter.clcikhere
import com.example.bookmyroom.Adapter.manually
import com.example.bookmyroom.databinding.ActivityRecycelBinding

class recycel : AppCompatActivity() {
    lateinit var  binding:ActivityRecycelBinding
    lateinit var listofdata:ArrayList<manually>
    lateinit var adapter:clcikhere
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRecycelBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
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
}