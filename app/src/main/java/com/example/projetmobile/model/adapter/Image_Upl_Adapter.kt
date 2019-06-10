package com.example.projetmobile.model.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.projetmobile.R

class Image_Upl_Adapter(var items: ArrayList<ImageView>) : RecyclerView.Adapter<Image_Upl_Adapter.ViewHolder1>() {

    class ViewHolder1(row: View) : RecyclerView.ViewHolder(row)  {
        var photo: ImageView? = null
        init {
            this.photo = row?.findViewById<ImageView>(R.id.imageView1)
        }
    }

    override fun onCreateViewHolder(p: ViewGroup, p1: Int): ViewHolder1 {
        val itemView = LayoutInflater.from(p?.context)
            .inflate(R.layout.image_upload, p, false)

        return Image_Upl_Adapter.ViewHolder1(itemView)
    }

    override fun onBindViewHolder(p: Image_Upl_Adapter.ViewHolder1, p1: Int) {
        var image = items[p1]
        p?.photo?.setImageResource(R.drawable.ic_launcher_background)
    }

    override fun getItemCount(): Int = items.size
}