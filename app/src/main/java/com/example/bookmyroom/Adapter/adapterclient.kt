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
import com.example.bookmyroom.DeatailedInterface
import com.example.bookmyroom.Detailedpage
import com.example.bookmyroom.R
import com.example.bookmyroom.clientdata
import com.example.bookmyroom.model.homedataclass
//private val deatiledInteface: DeatailedInterface
class adapterclient(
    var context: Context,
    var datalist: List<homedataclass>,

) : RecyclerView.Adapter<adapterclient.ViewHolder>() {
    class ViewHolder(var itemView: View) : RecyclerView.ViewHolder(itemView) {
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
        Glide.with(context).load(datalist[position].imagebathroom).into(holder.imageadapterbathroom)

        holder.cardviewdata.setOnClickListener {
//            val intent = Intent(context, Detailedpage::class.java)
//            intent.putExtra("hotelname", datalist[position].hotenmae)
//            intent.putExtra("description", datalist[position].description)
//            intent.putExtra("price", datalist[position].price)
//            intent.putExtra("city", datalist[position].city)
//            intent.putExtra("benefit", datalist[position].benefit)
//            intent.putExtra("address", datalist[position].addressofhotel)
//            intent.putExtra("roomsize", datalist[position].roomsize)
//
//            intent.putExtra("imageroom",datalist[position].image)
//            intent.putExtra("imageroomgetdeatile", datalist[position].imageroom)
//            intent.putExtra("imagebathroomgetdeatile", datalist[position].imagebathroom)
            val bundle = Bundle()
            bundle.putString("hotelname",datalist[position].hotenmae)
            bundle.putString("description", datalist[position].description)
            bundle.putString("price", datalist[position].price)
            bundle.putString("city",datalist[position].city)
            bundle.putString("benefit",datalist[position].benefit)
            bundle.putString("address",datalist[position].addressofhotel)
            bundle.putString("roomsize",datalist[position].roomsize)

            bundle.putString("imageroom", datalist[position].image.toString())
            bundle.putString("imageroomgetdeatile", datalist[position].imageroom.toString())
            bundle.putString("imagebathroomgetdeatile", datalist[position].imagebathroom.toString())

            val intent = Intent(context, Detailedpage::class.java)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
//
//        holder.cardviewdata.setOnClickListener {
//            deatiledInteface.getdeatile(
//                datalist[position].image,
//                datalist[position].hotenmae,
//                datalist[position].description,
//                datalist[position].price,
//                datalist[position].city,
//                datalist[position].benefit,
//                datalist[position].addressofhotel,
//                datalist[position].roomsize,
//                datalist[position].imageroom,
//                datalist[position].imagebathroom
//            )
//        }




    }

    fun searchdatalist(searchList:List<homedataclass>) {
        datalist = searchList
        notifyDataSetChanged()

    }
}