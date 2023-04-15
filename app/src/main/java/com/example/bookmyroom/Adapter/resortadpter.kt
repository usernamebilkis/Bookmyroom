package com.example.bookmyroom.Adapter

import android.content.Context
import android.content.Intent
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
import com.example.bookmyroom.model.homedataclass

class resortadpter(var context: Context, var list: List<homedataclass>):RecyclerView.Adapter<resortadpter.Viewholder>() {
    class Viewholder (var itemView: View):RecyclerView.ViewHolder(itemView){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.recysampleofhome, parent, false)
        return Viewholder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.hotelnameadapter.text = list[position].hotenmae
        holder.address.text = list[position].addressofhotel
        holder.price.text = list[position].price
        holder.cityada.text = list[position].city
        holder.benetfit.text = list[position].benefit
        holder.desc.text = list[position].description
        holder.roomsize.text = list[position].roomsize

        Glide.with(context).load(list[position].image).into(holder.imageadapter)
        Glide.with(context).load(list[position].imageroom).into(holder.imageadapterroom)
        Glide.with(context).load(list[position].imagebathroom).into(holder.imageadapterbathroom)


        holder.cardviewdata.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("hotelname",list[position].hotenmae)
            bundle.putString("description", list[position].description)
            bundle.putString("price", list[position].price)
            bundle.putString("city",list[position].city)
            bundle.putString("benefit",list[position].benefit)
            bundle.putString("address",list[position].addressofhotel)
            bundle.putString("roomsize",list[position].roomsize)
            bundle.putString("imageroom", list[position].image.toString())
            bundle.putString("imageroomgetdeatile", list[position].imageroom.toString())
            bundle.putString("imagebathroomgetdeatile", list[position].imagebathroom.toString())

            val intent = Intent(context, Detailedpage::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }


    fun searchdatalist(searchList:List<homedataclass>) {
        list = searchList
        notifyDataSetChanged()

    }
    }
