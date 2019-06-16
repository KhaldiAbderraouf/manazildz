package com.example.projetmobile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.example.projetmobile.model.adapter.Image_Adapter
import org.json.JSONObject
import kotlin.collections.ArrayList

class Detail : AppCompatActivity() {

    lateinit var list:ArrayList<String>
    lateinit var adapter:Image_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

//        setUpRecyclerView()

        var s = getIntent().getStringExtra("annonce")
        var int = JSONObject(s)

        set_data(int)

        findViewById<FloatingActionButton>(R.id.button_detail).setOnClickListener{
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 5)
            }
            else {
                startCall(int)
            }
        }
    }

    private fun set_data(int: JSONObject) {

        findViewById<TextView>(R.id.soustitre_detail).setText(int.get("titre").toString())
        findViewById<TextView>(R.id.date_detail).setText(int.get("depot").toString())
        findViewById<TextView>(R.id.willaya_detail).setText(int.get("willaya").toString())
        findViewById<TextView>(R.id.taille_detail).setText(int.get("taille").toString())
        findViewById<TextView>(R.id.prix_detail).setText(int.get("prix").toString())
        findViewById<TextView>(R.id.description_detail).setText(int.get("description").toString())
        findViewById<TextView>(R.id.numero_detail).setText(int.get("numero").toString())
        setUpRecyclerView()
        add_to_recy(to_array(int.get("photo").toString()))

    }

    private fun add_to_recy(s: ArrayList<String>) {
        for (i:Int in 0 .. s.size-1) {
            list.add(s.get(i))
            adapter.notifyItemInserted(i+1)
        }

    }

    private fun to_array(s:String): ArrayList<String> {
        var str = s
        str = str.replace("[","")
        str = str.replace("]","")
        str = str.replace("\"","")
        str = str.replace(" ","")

        var k = str.split(",")
        return ArrayList(k)
    }

    private fun setUpRecyclerView() {
        list = ArrayList()
        adapter = Image_Adapter(list)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_detail)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(adapter)
    }

    private fun startCall(int: JSONObject) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + int.get("numero").toString()))

        startActivity(intent)
    }
}
