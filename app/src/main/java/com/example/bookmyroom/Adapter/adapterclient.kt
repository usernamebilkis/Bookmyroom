package com.example.bookmyroom.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyroom.Detailedpage
import com.example.bookmyroom.R
import com.example.bookmyroom.model.foronlytest
import com.example.bookmyroom.model.homedataclass


//private val deatiledInteface: DeatailedInterface
class adapterclient(
    var context: Context,
    var datalist: List<homedataclass>,

) : RecyclerView.Adapter<adapterclient.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageadapter = itemView.findViewById<ImageView>(R.id.imagebuilding)
        var hotelnameadapter = itemView.findViewById<TextView>(R.id.hotelname)
        var address = itemView.findViewById<TextView>(R.id.addres)
        var price = itemView.findViewById<TextView>(R.id.pricerecy)
        var cityada = itemView.findViewById<TextView>(R.id.citytxt)
        var benetfit = itemView.findViewById<TextView>(R.id.benetfitrecysample)
        var desc = itemView.findViewById<TextView>(R.id.descsample)
        var roomsize = itemView.findViewById<TextView>(R.id.roomsizesample)
        var imageadapterroom = itemView.findViewById<ImageView>(R.id.roomimagesample)
        var imageadapterbathroom = itemView.findViewById<ImageView>(R.id.samplebathroom)
        var cardviewdata = itemView.findViewById<CardView>(R.id.cardview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.recysampleofhome, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return datalist.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.hotelnameadapter.text = datalist[position].hotenmae
        holder.address.text = datalist[position].addressofhotel
        holder.price.text = datalist[position].price
        holder.cityada.text = datalist[position].city
        holder.benetfit.text = datalist[position].benefit
        holder.desc.text = datalist[position].description
        holder.roomsize.text = datalist[position].roomsize

        Glide.with(context).load(datalist[position].image).into(holder.imageadapter)
        Glide.with(context).load(datalist[position].imageroom).into(holder.imageadapterroom)


        holder.cardviewdata.setOnClickListener {
           var intent = Intent(context,Detailedpage::class.java)
                      intent.putExtra("hotelname",datalist[holder.adapterPosition].hotenmae)
            intent.putExtra("description", datalist[holder.adapterPosition].description)
            intent.putExtra("price", datalist[holder.adapterPosition].price)
            intent.putExtra("city",datalist[holder.adapterPosition].city)
            intent.putExtra("benefit",datalist[holder.adapterPosition].benefit)
            intent.putExtra("address",datalist[holder.adapterPosition].addressofhotel)
            intent.putExtra("roomsize",datalist[holder.adapterPosition].roomsize)

            intent.putExtra("imagebuilding",datalist[holder.adapterPosition].image)
            intent.putExtra("imageroom",datalist[holder.adapterPosition].imageroom)

            context.startActivity(intent)
        }

    }
    fun searchdatalist(searchList:List<homedataclass>) {
        datalist = searchList
        notifyDataSetChanged()

    }
}