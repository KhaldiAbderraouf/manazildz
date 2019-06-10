package com.example.projetmobile.model.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projetmobile.R

class Image_Adapter(var items: ArrayList<ImageView>) : RecyclerView.Adapter<Image_Adapter.ViewHolder>() {

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row)  {
        var photo: ImageView? = null
        init {
            this.photo = row?.findViewById<ImageView>(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0?.context)
            .inflate(R.layout.element, p0, false)

        return Image_Adapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: Image_Adapter.ViewHolder, p1: Int) {
        var annonce = items[p1]
        p0?.photo?.setImageResource(R.mipmap.ic_launcher)
    }

    override fun getItemCount(): Int = items.size
}