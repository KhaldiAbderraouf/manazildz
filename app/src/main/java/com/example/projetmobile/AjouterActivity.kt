package com.example.projetmobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.example.projetmobile.model.adapter.Image_Upl_Adapter

class AjouterActivity : AppCompatActivity() {

    lateinit var List: ArrayList<ImageView>
    lateinit var Adapter: Image_Upl_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajouter_annonce)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        List = remplir_img()
        Adapter = Image_Upl_Adapter(List)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ajt)
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this,3)

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(Adapter)
    }

    private fun remplir_img(): ArrayList<ImageView> {
        val l : ArrayList<ImageView> = ArrayList<ImageView>()
        var photo = ImageView(this)
        photo.setImageResource(R.mipmap.ic_launcher)

        l.add(photo)
        l.add(photo)
        l.add(photo)
        l.add(photo)

        return l
    }
}
