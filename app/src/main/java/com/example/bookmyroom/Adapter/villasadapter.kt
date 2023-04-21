package com.example.bookmyroom.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyroom.Detailedpage
import com.example.bookmyroom.R
import com.example.bookmyroom.model.homedataclass

class villasadapter(var context: Context, var listofvillas:List<homedataclass>):RecyclerView.Adapter<villasadapter.Viewholder>(){
    class Viewholder(var itemView:View):RecyclerView.ViewHolder(itemView) {

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
     return listofvillas.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.hotelnameadapter.text = listofvillas[position].hotenmae
        holder.address.text = listofvillas[position].addressofhotel
        holder.price.text = listofvillas[position].price
        holder.cityada.text = listofvillas[position].city
        holder.benetfit.text = listofvillas[position].benefit
        holder.desc.text = listofvillas[position].description
        holder.roomsize.text = listofvillas[position].roomsize

        Glide.with(context).load(listofvillas[position].image).into(holder.imageadapter)
        Glide.with(context).load(listofvillas[position].imageroom).into(holder.imageadapterroom)



        holder.cardviewdata.setOnClickListener {
//            val bundle = Bundle()
//            bundle.putString("hotelname",listofvillas[holder.adapterPosition].hotenmae)
//            bundle.putString("description", listofvillas[holder.adapterPosition].description)
//            bundle.putString("price", listofvillas[holder.adapterPosition].price)
//            bundle.putString("city",listofvillas[holder.adapterPosition].city)
//            bundle.putString("benefit",listofvillas[holder.adapterPosition].benefit)
//            bundle.putString("address",listofvillas[holder.adapterPosition].addressofhotel)
//            bundle.putString("roomsize",listofvillas[holder.adapterPosition].roomsize)
//
//            bundle.putString("imagebuilding", listofvillas[holder.adapterPosition].image)
//            bundle.putString("imageroom", listofvillas[holder.adapterPosition].imageroom)
//            bundle.putString("imagebathroom", listofvillas[holder.adapterPosition].imagebathroom)

            val intent = Intent(context, Detailedpage::class.java)
            intent.putExtra("hotelname",listofvillas[holder.adapterPosition].hotenmae)
            intent.putExtra("description", listofvillas[holder.adapterPosition].description)
            intent.putExtra("price", listofvillas[holder.adapterPosition].price)
            intent.putExtra("city",listofvillas[holder.adapterPosition].city)
            intent.putExtra("benefit",listofvillas[holder.adapterPosition].benefit)
            intent.putExtra("address",listofvillas[holder.adapterPosition].addressofhotel)
            intent.putExtra("roomsize",listofvillas[holder.adapterPosition].roomsize)

            intent.putExtra("imagebuilding",listofvillas[holder.adapterPosition].image)
            intent.putExtra("imageroom",listofvillas[holder.adapterPosition].imageroom)

            context.startActivity(intent)
        }
    }


    fun searchdatalist(searchList:List<homedataclass>) {
        listofvillas = searchList
        notifyDataSetChanged()

    }
}