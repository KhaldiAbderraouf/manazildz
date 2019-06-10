package com.example.projetmobile.controler

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.projetmobile.R
import com.example.projetmobile.model.adapter.Image_Upl_Adapter

class Ajouter_Activity : AppCompatActivity() {

    lateinit var annonce_List: ArrayList<ImageView>
    lateinit var annonce_Adapter: Image_Upl_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        annonce_List = remplir()
        annonce_Adapter = Image_Upl_Adapter(annonce_List)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ajt)
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this,3)

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(annonce_Adapter)
    }

    private fun remplir(): ArrayList<ImageView> {
        val l : ArrayList<ImageView> = ArrayList<ImageView>()
        lateinit var photo: ImageView
        photo.setImageResource(R.mipmap.ic_launcher)

        l.add(photo)
        l.add(photo)
        l.add(photo)
        l.add(photo)

        return l
    }

}