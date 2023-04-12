package com.example.bookmyroom.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyroom.R
import com.example.bookmyroom.clientdata

data class manually(
        var image: Int,
    var hotelname: String? = null,
    var address: String? = null
)

class clcikhere(var context: Context, var list: ArrayList<manually>) :
    RecyclerView.Adapter<clcikhere.ViewHolder>() {
    class ViewHolder(var item: View) : RecyclerView.ViewHolder(item) {
        var imagedata = item.findViewById<ImageView>(R.id.imageView24)
        var title = item.findViewById<TextView>(R.id.textView8)
        var address = item.findViewById<TextView>(R.id.textView12)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inmfalte = LayoutInflater.from(context).inflate(R.layout.sample, parent, false)
        return ViewHolder(inmfalte)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentitem =  list[position]
        holder.imagedata.setImageResource(currentitem.image)
        holder.title.text=currentitem.hotelname
        holder.address.text=currentitem.address
    }
}